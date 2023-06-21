package com.example.reservation.mainService.impl;

import com.example.reservation.mainService.MainService;
import com.example.reservation.member.data.dto.MemberDTO;
import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MemberRepository memberRepository;

    public boolean save(MemberDTO memberDTO) {

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()) {
            return false;
        }

        String encPassword = BCrypt.hashpw(memberDTO.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        memberRepository.save(
                MemberEntity.builder()
                        .userId(memberDTO.getUserId())
                        .userName(memberDTO.getUserName())
                        .phone(memberDTO.getPhone())
                        .password(encPassword)
                        .isPartner(memberDTO.getIsPartner())
                        .build());
        return true;
    }


    /*
    UserDetailService
    = 사용자 인증을 위해 사용하는 인터페이스로, 사용자 정보를 제공하는 역할을 한다.

    권한 정보를 grantedAuthorites 에 추가하여 user를 리턴한다
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(username);
        if (!byUserId.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        MemberEntity memberEntity = byUserId.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (memberEntity.getIsPartner() == 1) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PARTNER"));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(memberEntity.getUserId(), memberEntity.getPassword(), grantedAuthorities);
    }

}
