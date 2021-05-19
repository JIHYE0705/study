package com.fastcampus.study.repository;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {


    // Dependency Injection (DI) - Singleton
    @Autowired  // 객체생성
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user (%s, %s, %d) value (account, email, age);

        User user = new User();
        //user.setId(); DB에서 자동생성
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);


    }
    public void read() {


    }
    public void update() {

    }

    public void delete() {

    }
}
