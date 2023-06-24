package com.example.reservation.member.data.entity;

import com.example.reservation.member.data.dto.MemberDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(name = "member_id")
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

    @OneToMany(mappedBy = "memberEntity")
    private List<ShopEntity> shopEntities = new ArrayList<>();

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
