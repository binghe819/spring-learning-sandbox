package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;

import com.binghe.domain.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
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
        User invalidUser = new User("", false, 0, "binghe");

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);

        // then
        violations.forEach(violation -> {
            System.out.printf("검사 필드: %s, 유효하지 않은 값: [%s], 메시지: %s \n", violation.getPropertyPath(), violation.getInvalidValue(), violation.getMessage());
        });
    }
}
