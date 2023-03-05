package com.hmdp;

import com.hmdp.service.IShopService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HmDianPingApplicationTests {

    @Resource
    IShopService shopService;

    @Test
    void testSaveShop(){
        shopService.saveShop2Redis(1L, 10L);
    }


}
