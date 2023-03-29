package com.wang.userapp;

import com.wang.userapp.domain.TCoupon;
import com.wang.userapp.domain.TCouponExample;
import com.wang.userapp.mapper.TCouponMapper;
import com.wang.userapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class userappApplicationTests {



    @Resource
    private UserService couponService;

    @Resource
    private TCouponMapper tCouponMapper;

    @Test
    public void contextLoads() {
        couponService.print();
        System.err.println("hello world");
    }

    @Test
    public void insert(){
        for(int i=0;i<100000;i++){
            TCoupon tCoupon = new TCoupon();
            tCoupon.setAchieveAmount(500);
            tCoupon.setReduceAmount(20);
            tCoupon.setCreateTime(new Date());
            tCoupon.setCode(UUID.randomUUID().toString());
            tCoupon.setPicUrl("1.jpg");
            tCoupon.setStatus(0);
            tCoupon.setStock(100);
            tCoupon.setTitle("测试coupon");
            tCouponMapper.insert(tCoupon);
        }

    }

    @Test
    public void delete(){
        tCouponMapper.deleteByPrimaryKey(7);
    }

    @Test
    public void update(){
        TCoupon tCoupon = new TCoupon();
        tCoupon.setId(8);
        tCoupon.setCode("9527");
        tCouponMapper.updateByPrimaryKeySelective(tCoupon);
        tCouponMapper.updateByPrimaryKey(tCoupon);
    }


    @Test
    public void select(){
        // select * from t_coupon where code = "00415d96-49bd-4cce-83e3-08302b9aa084" and status=0 and achieve_amount between (100,1000) and title not like '%111%';
        TCouponExample example = new TCouponExample();
        example.createCriteria().andCodeEqualTo("fd7911b8-81e5-4dac-bdbe-b666e684390e").andStatusEqualTo(0)
                .andAchieveAmountBetween(100,1000).andTitleNotLike("111");
        List<TCoupon> tCoupon =  tCouponMapper.selectByExample(example);
        System.err.println(tCoupon);
    }

}
