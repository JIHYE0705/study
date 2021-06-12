package com.fastcampus.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity // order_detail
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {"user", "item"}) //상호참조하던 내용 제외
@ToString(exclude = {"orderGroup", "item"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;


    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

    // OrderDetail (N) : (1) Item
    @ManyToOne
    private Item item;

    // OrderDetail (N) : (1) OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

//    // N : 1
//    @ManyToOne
//    private User user;  // user_id
//
//    // N : 1
//    @ManyToOne
//
//    private Item item;

}
