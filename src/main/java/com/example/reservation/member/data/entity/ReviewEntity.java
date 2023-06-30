package com.example.reservation.member.data.entity;

import com.example.reservation.partner.data.entity.ShopEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long star;

    private String comments;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;


    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

}
