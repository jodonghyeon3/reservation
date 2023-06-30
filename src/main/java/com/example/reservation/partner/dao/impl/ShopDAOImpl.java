package com.example.reservation.partner.dao.impl;

import com.example.reservation.type.ReservationStatus;
import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.member.repository.MemberRepository;
import com.example.reservation.member.repository.ReservationRepository;
import com.example.reservation.partner.dao.ShopDAO;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.reservation.type.ReservationStatus.*;
import static com.example.reservation.member.dao.impl.MemberDAOImpl.getShopDTOS;

@Service
@RequiredArgsConstructor
public class ShopDAOImpl implements ShopDAO {

    private final ShopRepository shopRepository;

    private final MemberRepository memberRepository;

    private final ReservationRepository reservationRepository;

    @Override
    public List<ShopDTO> findShopListByUserId(String userId) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(userId);
        MemberEntity memberEntity = byUserId.get();
        List<ShopEntity> shopEntityList = memberEntity.getShopEntities();

        return getShopDTOS(shopEntityList);
    }

    @Override
    public void saveShop(ShopEntity shopEntity, String userId) {

        Optional<MemberEntity> byUserId = memberRepository.findByUserId(userId);
        MemberEntity memberEntity = byUserId.get();
        shopEntity.setMemberEntity(memberEntity);
        shopRepository.save(shopEntity);
    }

    @Override
    public List<ShopEntity> findReservationListByUserId(String userId) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(userId);
        Long memberId = byUserId.get().getId();
        System.out.println("memberId = " + memberId);
        List<ShopEntity> byMemberEntityId = shopRepository.findByMemberEntityId(memberId);

        return byMemberEntityId;

    }

    @Override
    public void updateStatus(String status, Long reserId) {
        ReservationEntity reservationEntity = reservationRepository.findById(reserId).get();
        ReservationStatus reserStatus = null;
        if (status.equals("대기")) reserStatus = WAIT;
        else if (status.equals("승인")) reserStatus = APPROVE;
        else if (status.equals("취소")) reserStatus = CANCEL;
        reservationEntity.setReservationStatus(reserStatus);
        reservationRepository.save(reservationEntity);
    }
}
