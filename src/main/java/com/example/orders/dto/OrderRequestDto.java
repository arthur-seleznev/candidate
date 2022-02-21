package com.example.orders.dto;

import com.example.orders.validation.ValueOfEnum;
import com.example.orders.enums.ProductType;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class OrderRequestDto {
    @Digits(integer = 13, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Currency is necessary field")
    private String currency;

    @NotNull(message = "ProductType is necessary field")
    @ValueOfEnum(enumClass = ProductType.class)
    private String productType;

    @NotNull(message = "Links is necessary field")
    @Size(min = 1, message = "Links should not be empty")
    private Set<
            @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)",
            message = "Links should be represented by valid urls")
            String
            > links;

    public OrderRequestDto(BigDecimal price, String currency, String productType, Set<String> links) {
        this.price = price;
        this.currency = currency;
        this.productType = productType;
        this.links = links;
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
