package org.example.alomia_leccion2.models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.alomia_leccion2.models.entities.OrderStatus;

import java.math.BigDecimal;
import java.util.Currency;

public class PurchaseOrderRequestDTO {
    @NotBlank
    private String orderNumber;

    @NotBlank
    private String supplierName;

    @NotNull
    private OrderStatus status;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private Currency currency;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
