package com.example.reservation.partner.repository;

import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.partner.data.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
    Optional<ShopEntity> findByShopName(String shopName);

    List<ShopEntity> findAllByOrderByShopNameAsc();
    List<ShopEntity> findByMemberEntityId(Long id);
    List<ShopEntity> findAllByOrderByStarDesc();
}
