package com.example.reservation.member.data.dto;

import com.example.reservation.ReservationStatus;
import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.data.entity.ReservationEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private LocalDateTime date;
    private ReservationStatus reservationStatus;

    public ReservationEntity toEntity() {
        return ReservationEntity.builder()
                .date(date)
                .reservationStatus(reservationStatus)
                .build();
    }
}
