package com.binghe;

import static org.assertj.core.api.Assertions.assertThat;

import com.binghe.redis_repository.Address;
import com.binghe.redis_repository.Person;
import com.binghe.redis_repository.PersonRedisRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

@DataRedisTest
public class RedisRepositoryTest {

    @Autowired
    private PersonRedisRepository redisRepository;

    @Test
    void saveAndFind() {
        // given
        Address address = new Address("seoul, korea");
        Person person = new Person(null, "first", "last", address);

        // when
        Person savedPerson = redisRepository.save(person);

        // then
        Optional<Person> findPerson = redisRepository.findById(savedPerson.getId());
        findPerson.orElse(null);
        assertThat(findPerson).isNotNull();
        assertThat(findPerson.get().getFirstname()).isEqualTo("first");
        assertThat(findPerson.get().getAddress().getAddress()).isEqualTo("seoul, korea");
    }

}
