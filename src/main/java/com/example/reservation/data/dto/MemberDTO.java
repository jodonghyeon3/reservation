package com.example.reservation.data.dto;

import com.example.reservation.data.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String userId;

    private String userName;

    private String password;

    private String phone;

    private Long isPartner;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .phone(phone)
                .isPartner(isPartner)
                .build();
    }
}
