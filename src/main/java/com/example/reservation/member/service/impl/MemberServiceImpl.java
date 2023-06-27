package com.example.reservation.member.service.impl;

import com.example.reservation.member.dao.MemberDAO;
import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.member.service.MemberService;
import com.example.reservation.partner.data.dto.ShopDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;



    @Override
    public List<ShopDTO> shopList() {
        return memberDAO.shopListAll();
    }

    @Override
    public ShopDTO shopInfo(String shopName) {
        return memberDAO.shopInfo(shopName);
    }

    @Override
    public void reservation(String shopName, LocalDate date, String userId) {

        memberDAO.reservation(shopName, date, userId);
    }

    @Override
    public List<ReservationEntity> findByUserId(String userId) {
        return memberDAO.findByUserIdFromReservation(userId);
    }

    @Override
    public void saveReview(Long resId, String comments, Long star) {
        memberDAO.saveReview(resId, comments, star);
    }
}
