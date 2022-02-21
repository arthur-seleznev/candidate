package com.example.orders.controller;

import com.example.orders.dto.OrderRequestDto;
import com.example.orders.dto.OrderResponseDto;
import com.example.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public @ResponseBody OrderResponseDto add(@RequestBody @Valid OrderRequestDto payload) {
        return orderService.handleOrderRequestAndReturnResponse(payload);
    }
}