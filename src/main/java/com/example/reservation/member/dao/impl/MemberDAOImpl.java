package com.example.reservation.member.dao.impl;

import com.example.reservation.member.dao.MemberDAO;
import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.data.entity.ReservationEntity;
import com.example.reservation.member.data.entity.ReviewEntity;
import com.example.reservation.member.repository.MemberRepository;
import com.example.reservation.member.repository.ReservationRepository;
import com.example.reservation.member.repository.ReviewRepository;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import com.example.reservation.type.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.reservation.type.ReservationStatus.WAIT;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberDAOImpl implements MemberDAO {

    private final ShopRepository shopRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void reservation(String shopName, LocalDateTime date, String userId) {
        ShopEntity shopEntity = shopRepository.findByShopName(shopName)
                .orElseThrow(() -> new IllegalArgumentException("상점이 존재하지 않습니다." + shopName));

        MemberEntity memberEntity = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 이름이 존재하지 않습니다." + userId));

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
        ShopEntity shopEntity = shopRepository.findByShopName(shopName)
                .orElseThrow(() -> new IllegalArgumentException("상점 이름이 없습니다." + shopName));

        return ShopDTO.builder()
                .shopName(shopEntity.getShopName())
                .description(shopEntity.getDescription())
                .address(shopEntity.getAddress())
                .lng(shopEntity.getLng())
                .lat(shopEntity.getLat())
                .stars(shopEntity.getStar())
                .build();
    }

    @Override
    public List<ReservationEntity> findByUserIdFromReservation(String userId) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 아이디가 없습니다." + userId));

        return reservationRepository.findByMemberEntityId(memberEntity.getId());
    }

    @Override
    public void saveReview(Long resId, String comments, Long star) {
        ReservationEntity reservationEntity = reservationRepository.findById(resId)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보가 없습니다." + resId));

        ReviewEntity reviewEntity = ReviewEntity.builder()
                .reservationEntity(reservationEntity)
                .comments(comments)
                .star(star)
                .build();
        reservationEntity.setReservationStatus(ReservationStatus.FINISHED);
        reservationRepository.save(reservationEntity);

        ShopEntity shopEntity = reservationEntity.getShopEntity();
        List<ReviewEntity> reviewEntityList = shopEntity.getReviewEntity();

        reviewEntityList.add(reviewEntity);
        shopEntity.setReviewEntity(reviewEntityList);

        double averageStar = reviewEntityList.stream()
                .mapToLong(ReviewEntity::getStar)
                .average()
                .orElse(0.0);

        shopEntity.setStar(averageStar);

        shopRepository.save(shopEntity);
        reviewRepository.save(reviewEntity);

    }

    @Override
    public List<ShopDTO> shopListSort(String sort) {
        List<ShopDTO> shopDTOList = new ArrayList<>();

        if (sort.equals("name")) {
            List<ShopEntity> allByOrderByStoreNameAsc = shopRepository.findAllByOrderByShopNameAsc();
            for (ShopEntity shop : allByOrderByStoreNameAsc) {
                ShopDTO shopDTO = createShopDTO(shop);
                shopDTOList.add(shopDTO);
            }
        } else if (sort.equals("star")) {
            List<ShopEntity> allByOrderByStarDesc = shopRepository.findAllByOrderByStarDesc();
            for (ShopEntity shop : allByOrderByStarDesc) {
                ShopDTO shopDTO = createShopDTO(shop);
                shopDTOList.add(shopDTO);
            }
        }

        return shopDTOList;
    }

    @Override
    public void checkIn(Long reserId) {
        ReservationEntity reservationEntity = reservationRepository.findById(reserId)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보가 없습니다." + reserId));

        reservationEntity.setReservationStatus(ReservationStatus.CHECKIN);

        reservationRepository.save(reservationEntity);
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
                    .stars(shopEntity.getStar())
                    .build();
            shopDTOList.add(shopDTO);
        }
        return shopDTOList;
    }

    private ShopDTO createShopDTO(ShopEntity shop) {
        return ShopDTO.builder()
                .shopName(shop.getShopName())
                .memberEntity(shop.getMemberEntity())
                .lng(shop.getLng())
                .lat(shop.getLat())
                .address(shop.getAddress())
                .description(shop.getDescription())
                .stars(shop.getStar())
                .build();
    }
}
