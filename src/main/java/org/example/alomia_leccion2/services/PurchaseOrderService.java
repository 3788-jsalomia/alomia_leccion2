package org.example.alomia_leccion2.services;

import org.example.alomia_leccion2.models.DTO.PurchaseOrderRequestDTO;
import org.example.alomia_leccion2.models.DTO.PurchaseOrderResponseDTO;
import org.example.alomia_leccion2.models.entities.OrderStatus;
import org.example.alomia_leccion2.models.entities.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderResponseDTO save(PurchaseOrderRequestDTO request);

    List<PurchaseOrderResponseDTO> findAll(
            String q,
            OrderStatus status,
            Currency currency,
            BigDecimal minTotal,
            BigDecimal maxTotal,
            LocalDateTime from,
            LocalDateTime to
    );



}
