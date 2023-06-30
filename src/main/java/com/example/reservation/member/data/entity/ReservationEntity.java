package com.example.reservation.member.data.entity;

import com.example.reservation.type.ReservationStatus;
import com.example.reservation.partner.data.entity.ShopEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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

//    private LocalDate date;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime date;

    @OneToOne(mappedBy = "reservationEntity")
    private ReviewEntity reviewEntity;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

//    public ReservationDTO toDto() {
//        return ReservationDTO.builder()
//                .date(date)
//                .reservationStatus(reservationStatus)
//                .build();
//    }
}
