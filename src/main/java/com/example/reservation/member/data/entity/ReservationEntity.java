package com.example.reservation.member.data.entity;

import com.example.reservation.ReservationStatus;
import com.example.reservation.member.data.dto.MemberDTO;
import com.example.reservation.member.data.dto.ReservationDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public ReservationDTO toDto() {
        return ReservationDTO.builder()
                .date(date)
                .reservationStatus(reservationStatus)
                .build();
    }
}
