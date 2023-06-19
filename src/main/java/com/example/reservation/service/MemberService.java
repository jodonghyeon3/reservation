package com.example.reservation.service;

import com.example.reservation.data.dto.MemberDTO;
import com.example.reservation.data.entity.MemberEntity;
import com.example.reservation.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean save(MemberDTO memberDTO) {

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if (byUserId.isPresent()) {
            return false;
        }

        memberRepository.save(
                MemberEntity.builder()
                        .userId(memberDTO.getUserId())
                        .userName(memberDTO.getUserName())
                        .phone(memberDTO.getPhone())
                        .password(memberDTO.getPassword())
                        .isPartner(memberDTO.getIsPartner())
                        .build());

        return true;
    }

    public MemberDTO login(MemberDTO memeberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memeberDTO.getUserId());
        if (byUserId.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byUserId.get();
            if (memberEntity.getPassword().equals(memeberDTO.getPassword())) {
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


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<ManagerDTO> optionalManager = managerRepository.findById(username);
//        if (!optionalManager.isPresent()) {
//            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.")
//        }
//
//        ManagerDTO manager = optionalManager.get();
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        return new User(manager.getId, manager.getPassword, grantedAuthorities);
//    }
}
