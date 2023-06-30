package com.example.reservation.member.data.dto;

import com.example.reservation.member.data.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userId;

    private String userName;

    private String password;

    private String phone;

    private Long isPartner;




//    public MemberEntity toEntity() {
//        return MemberEntity.builder()
//                .userId(userId)
//                .userName(userName)
//                .password(password)
//                .phone(phone)
//                .isPartner(isPartner)
//                .build();
//    }
}
