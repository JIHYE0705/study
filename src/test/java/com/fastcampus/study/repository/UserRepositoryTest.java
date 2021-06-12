package com.fastcampus.study.repository;

import com.fastcampus.study.StudyApplicationTests;
import com.fastcampus.study.model.entity.Item;
import com.fastcampus.study.model.entity.User;
import com.fastcampus.study.model.enumclass.UserStatus;
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
//        // String sql = insert into user (%s, %s, %d) value (account, email, age);
//


        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";



        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();


        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);



    }
    @Test
    @Transactional
    public void read() {


        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user
                .setEmail("")
                .setPhoneNumber("")
                .setStatus(UserStatus.REGISTERED);

        User u = new User().setAccount("").setEmail("").setPassword("");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("---------------------- 주문묶음 ----------------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("---------------------- 주문상세 ----------------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 성태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());


                });
            });
        }

        Assertions.assertNotNull(user);




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
