package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import com.binghe.domain.Article;
import com.binghe.domain.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HibernateValidatorTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("유효성 검증 학습 테스트 - 검증 결과를 출력한다.")
    @Test
    void validate() {
        // given
        User invalidUser = new User();
        invalidUser.setName("");
        invalidUser.setWorking(false);
        invalidUser.setAge(0);
        invalidUser.setEmail("binghe");

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);

        // then
        assertThat(violations.size()).isEqualTo(4);
        violations.forEach(violation -> {
            System.out.printf("검사 필드: %s, 유효하지 않은 값: [%s], 메시지: %s \n", violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage());
        });
    }

    @DisplayName("유효성 검증 학습 테스트 (@Valid) - 중첩된 객체일 경우 @Valid를 붙여주어야 한다.")
    @Test
    void nestedValidation() {
        // given
        Article invalidArticle = new Article();
        invalidArticle.setTitle("");
        invalidArticle.setContent("");
        invalidArticle.setCreatedAt(LocalDateTime.now());

        User validUser = new User("binghe", true, 20, "binghe@naver.com");
        validUser.setArticles(List.of(invalidArticle));

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);

        // then
        assertThat(violations.size()).isEqualTo(2);
        violations.forEach(violation -> {
            System.out.println(violation.toString());
        });
    }
}
