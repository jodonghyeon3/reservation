package com.example.reservation.partner.dao.impl;

import com.example.reservation.member.data.entity.MemberEntity;
import com.example.reservation.member.repository.MemberRepository;
import com.example.reservation.partner.dao.ShopDAO;
import com.example.reservation.partner.data.dto.ShopDTO;
import com.example.reservation.partner.data.entity.ShopEntity;
import com.example.reservation.partner.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.reservation.member.dao.impl.MemberDAOImpl.getShopDTOS;

@Service
@RequiredArgsConstructor
public class ShopDAOImpl implements ShopDAO {

    private final ShopRepository shopRepository;

    private final MemberRepository memberRepository;

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
}
