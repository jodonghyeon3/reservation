package com.example.reservation.member.data.entity;

import com.example.reservation.member.data.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String phone;


    @Column
    private Long isPartner;

    public MemberDTO toDto() {
        return MemberDTO.builder()
                .userId(userId)
                .userName(userName)
                .password(password)
                .phone(phone)
                .isPartner(isPartner)
                .build();
    }

}
