package com.example.reservation.member.dao;

import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.partner.data.dto.ShopDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MemberDAO {

    void reservation(String shopName, LocalDate date, String userId);

    List<ShopDTO> shopListAll();

    ShopDTO shopInfo(String shopName);

    List<ReservationEntity> findByUserIdFromReservation(String userId) throws UsernameNotFoundException;

    void saveReview(Long resId, String comments, Long star);
}
