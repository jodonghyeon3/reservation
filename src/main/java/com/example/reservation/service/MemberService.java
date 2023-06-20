package com.example.reservation.service;

import com.example.reservation.data.dto.MemberDTO;
import com.example.reservation.data.entity.MemberEntity;
import com.example.reservation.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byUserId.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                // 비밀번호 일치
                return MemberDTO.builder()
                        .userId(memberEntity.getUserId())
                        .userName(memberEntity.getUserName())
                        .password(memberEntity.getPassword())
                        .phone(memberEntity.getPhone())
                        .isPartner(memberEntity.getIsPartner())
                        .build();
            } else {
                // 비밀번호 불일치(로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;

        }
    }

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
