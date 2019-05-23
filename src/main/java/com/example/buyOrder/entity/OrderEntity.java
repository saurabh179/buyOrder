package com.example.buyOrder.entity;


import javax.persistence.*;

@Entity
@Table(name = "orderTable")
public class OrderEntity {




    @Id
    @GeneratedValue
    private Integer orderId;

    @Column(name = "email")
    private String email;

    @Column(name = "productId")
    private String productId;

    @Column(name = "variantId")
    private String variantId;

    @Column(name = "merchantId")
    private String merchantId;

    @Column(name = "quantity")
    private Integer quantity;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
