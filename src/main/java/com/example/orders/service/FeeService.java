package com.example.orders.service;

import com.example.orders.enums.ProductType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FeeService {
    public BigDecimal calculateFee(BigDecimal price, String productType) {
        BigDecimal result;

        if (productType.equals(ProductType.BOND.name())) {
            result = price.multiply(BigDecimal.valueOf(0.02));
        } else if (productType.equals(ProductType.STOCK.name())) {
            result = price.multiply(BigDecimal.valueOf(0.01)).max(BigDecimal.valueOf(99.00));
        } else {
            throw new IllegalArgumentException();
        }

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
