package com.example.reservation.member.dao.impl;

import com.example.reservation.ReservationStatus;
import com.example.reservation.member.dao.MemberDAO;
import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.member.repository.MemberRepository;
import com.example.reservation.member.repository.ReservationRepository;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.reservation.ReservationStatus.WAIT;

@RequiredArgsConstructor
@Service
public class MemberDAOImpl implements MemberDAO {

    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void reservation(String shopName, LocalDate date, String userId) {
        Optional<ShopEntity> byShopName = shopRepository.findByShopName(shopName);
        ShopEntity shopEntity = byShopName.get();
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(userId);
        MemberEntity memberEntity = byUserId.get();

        reservationRepository.save(ReservationEntity.builder()
                .memberEntity(memberEntity)
                .shopEntity(shopEntity)
                .date(date)
                .reservationStatus(WAIT)
                .build());
    }

    @Override
    public List<ShopDTO> shopListAll() {
        List<ShopEntity> shopEntityList = shopRepository.findAll();
        return getShopDTOS(shopEntityList);
    }

    @Override
    public ShopDTO shopInfo(String shopName) {
        Optional<ShopEntity> byShopName = shopRepository.findByShopName(shopName);
        ShopEntity shopEntity = byShopName.get();
        return ShopDTO.builder()
                .shopName(shopEntity.getShopName())
                .description(shopEntity.getDescription())
                .address(shopEntity.getAddress())
                .lng(shopEntity.getLng())
                .lat(shopEntity.getLat())
                .build();
    }

    public static List<ShopDTO> getShopDTOS(List<ShopEntity> shopEntityList) {
        List<ShopDTO> shopDTOList = new ArrayList<>();

        for (ShopEntity shopEntity : shopEntityList) {
            ShopDTO shopDTO = ShopDTO.builder()
                    .shopName(shopEntity.getShopName())
                    .description(shopEntity.getDescription())
                    .address(shopEntity.getAddress())
                    .lng(shopEntity.getLng())
                    .lat(shopEntity.getLat())
                    .build();
            shopDTOList.add(shopDTO);
        }
        return shopDTOList;
    }
}
