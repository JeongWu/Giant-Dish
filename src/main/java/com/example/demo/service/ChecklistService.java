package com.example.demo.service;


import com.example.demo.domain.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChecklistService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    private final ChecklistRepository checkRepository;
    private final DeliveryRepository deliveryRepository;
    /**
     * 주문 / 취소 / 검색 로직을 담겨 있습니다.
     */

    @Transactional
    public Long Check(Long memberId, Long foodId, Long storeId, Long orderId, Long deliveryid) {


        Member member = memberRepository.findOne(memberId);
        Food food = foodRepository.findOne(foodId);
        Store store = storeRepository.findOne(storeId);
        Order order = orderRepository.findOne(orderId);
        Delivery delivery = deliveryRepository.findOne(deliveryid);


        Checklist checklist = Checklist.createchecklist(member,order,food,store,delivery);
        checkRepository.save(checklist);
        return checklist.getId();
    }

}


