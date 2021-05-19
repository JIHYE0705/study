package com.fastcampus.study.repository;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

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
    @Test
    public void read() {

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });


    }

    @Test
    public void update() {

        //update user set account = %?...

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
           //  selectUser.setId(3L); ID 가 2가 아닌 3으로 select되어버림
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });


    }

    public void delete() {

    }
}
