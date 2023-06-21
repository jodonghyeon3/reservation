package com.example.reservation.member.repository;

import com.example.reservation.member.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository
        extends JpaRepository<MemberEntity, Long> {
    // 이메일로 회원 정보 조회 (select * from member_table where user_id=?)
    Optional<MemberEntity> findByUserId(String userId);
}
