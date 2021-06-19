package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入结果"+result);
        if(result>0){
            return new CommonResult(200,"插入成功,端口"+serverPort,result);
        }else {
            return new CommonResult(444,"插入失败,端口"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment result=paymentService.getPaymentById(id);
        log.info("查询结果"+result);
        if(result!=null){
            return new CommonResult(200,"查询成功,端口"+serverPort,result);
        }else {
            return new CommonResult(444,"没有对应记录,"+id+"端口"+serverPort,null);
        }
    }
}
