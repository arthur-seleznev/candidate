package com.example.orders.service;

import com.example.orders.domain.Link;
import com.example.orders.domain.Order;
import com.example.orders.dto.OrderRequestDto;
import com.example.orders.dto.OrderResponseDto;
import com.example.orders.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {
    private final FeeService feeService;
    private final OrderRepo orderRepo;
    private final MailService mailService;

    public OrderService(FeeService feeService, OrderRepo orderRepo, MailService mailService) {
        this.feeService = feeService;
        this.orderRepo = orderRepo;
        this.mailService = mailService;
    }

    private Order createOrderFromRequest(OrderRequestDto payload) {
        Order order = new Order();

        order.setPrice(payload.getPrice());
        order.setCurrency(payload.getCurrency());
        order.setProductType(payload.getProductType());
        order.setFee(feeService.calculateFee(payload.getPrice(), payload.getProductType()));
        order.setLinks(payload.getLinks().stream()
                .map(l -> l.replaceFirst("(?i)^http://", "https://"))
                .map(l -> new Link(l, order))
                .collect(Collectors.toSet()));

        return order;
    }

    private OrderResponseDto createResponseFromOrder(Order order) {
        return new OrderResponseDto(
                order.getId(), order.getPrice(), order.getCurrency(), order.getFee(), order.getProductType(),
                order.getLinks().stream().map(l -> l.getValue()).collect(Collectors.toSet())
        );
    }

    public OrderResponseDto handleOrderRequestAndReturnResponse(OrderRequestDto payload) {
        Order order = createOrderFromRequest(payload);
        orderRepo.save(order);
        OrderResponseDto response = createResponseFromOrder(order);
        mailService.send(
                "arturselez@yandex.ru",
                "Новый заказ " + order.getId(),
                response.getLinks().stream()
                        .map(l -> l + "\n")
                        .collect(Collectors.joining())
        );
        return response;
    }
}
