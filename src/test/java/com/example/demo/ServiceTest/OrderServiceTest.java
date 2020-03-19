package com.example.demo.ServiceTest;


import com.example.demo.domain.Address;
import com.example.demo.domain.Food;
import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FoodService;
import com.example.demo.service.MemberService;
import com.example.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.domain.Coupon.천원;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodService foodService;


    //주문적용
    @Test
    public void inser_test() throws Exception{
        String name = "현우";
        String street = "서울시";
        String zipcode ="강남구";
        String Foodname = "치킨";
        int price = 10000;

        Address address = new Address(zipcode,street);

        memberRepository.save(Member.builder()
                .name(name)
                .address(address)
                .coupon(천원)
                .build());


        Food food = new Food(); //배송량
        food.setName(Foodname);
        food.setPrice(price);
        foodRepository.save(food);
        Food foodId = foodRepository.findOne(food.getId());

        List<Member> mm = memberRepository.findAll();
        orderService.order(mm.get(0).getId(), foodId.getId(), 3);

    }

}