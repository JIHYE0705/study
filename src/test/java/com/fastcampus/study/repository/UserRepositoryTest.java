package com.fastcampus.study.repository;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.Item;
import com.fastcampus.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
    @Transactional
    public void read() {
        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccount("TestUser03");

//        user.ifPresent(selectUser ->{
//            System.out.println("user : " + selectUser);
//            System.out.println("email : " + selectUser.getEmail());
//        });

        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(detail -> {

                Item item = detail.getItem();
                System.out.println(item);
            });
        });


    }

    @Test
   // @Transactional  테스트 후 원래상태로 되돌리고싶을때
    public void update() {

        //update user set account = %?...

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
           //  selectUser.setId(3L); ID 2가 아닌 3이 select되어버림
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });


    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(3L);


        Assertions.assertTrue(user.isPresent()); // true


        user.ifPresent(selectUser -> {
            //  selectUser.setId(3L); ID 2가 아닌 3이 delete되어버림
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);


        Assertions.assertFalse(deleteUser.isPresent()); // false


//        if(deleteUser.isPresent()) {
//            System.out.println("데이터 존재" + deleteUser.get());
//        }
//        else {
//            System.out.println("데이터 삭제 데이터 없음");
//        }
    }
}
