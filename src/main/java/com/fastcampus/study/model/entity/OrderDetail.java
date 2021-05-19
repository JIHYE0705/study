package com.fastcampus.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // order_detail
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "item"}) //상호참조하던 내용 제외
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime orderAt;

    // N : 1
    @ManyToOne
    private User user;  // user_id

    // N : 1
    @ManyToOne

    private Item item;

}
