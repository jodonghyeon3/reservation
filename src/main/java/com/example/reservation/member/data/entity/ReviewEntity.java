package com.example.reservation.member.data.entity;

import lombok.*;

import javax.persistence.*;

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


}
