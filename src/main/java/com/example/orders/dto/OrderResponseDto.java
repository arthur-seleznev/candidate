package com.example.orders.dto;

import com.example.orders.validation.ValueOfEnum;
import com.example.orders.enums.ProductType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

public class OrderResponseDto {
    @NotNull
    private long id;

    @Digits(integer = 13, fraction = 2)
    private BigDecimal price;

    @NotNull
    private String currency;

    @Digits(integer = 13, fraction = 2)
    private BigDecimal fee;

    @NotNull()
    @ValueOfEnum(enumClass = ProductType.class)
    private String productType;

    @NotNull
    @Size(min = 1)
    private Set<String> links;

    public OrderResponseDto(long id, BigDecimal price, String currency, BigDecimal fee, String productType, Set<String> links) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.fee = fee;
        this.productType = productType;
        this.links = links;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Set<String> getLinks() {
        return links;
    }

    public void setLinks(Set<String> links) {
        this.links = links;
    }
}
