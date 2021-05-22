package com.fastcampus.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Table(name = "user") //Classname 과 Tablename이 같으면 생략가능

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroup"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "account") DB와 이름이 같으면 자동매칭
    private String account;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // User (1) : (N)OrderGroup

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

    // 1 : N
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<OrderDetail> orderDetailList;


}
