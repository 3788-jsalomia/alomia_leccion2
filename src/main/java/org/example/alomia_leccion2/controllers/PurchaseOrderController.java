package org.example.alomia_leccion2.controllers;

import jakarta.validation.Valid;
import org.example.alomia_leccion2.models.DTO.PurchaseOrderRequestDTO;
import org.example.alomia_leccion2.models.DTO.PurchaseOrderResponseDTO;
import org.example.alomia_leccion2.models.entities.Currency;
import org.example.alomia_leccion2.models.entities.OrderStatus;
import org.example.alomia_leccion2.models.entities.PurchaseOrder;
import org.example.alomia_leccion2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    // SAVE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrderResponseDTO save(
            @Valid @RequestBody PurchaseOrderRequestDTO request) {
        return service.save(request);
    }

    // GET ALL

    @GetMapping
    public List<PurchaseOrderResponseDTO> getAll(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Currency currency,
            @RequestParam(required = false) BigDecimal minTotal,
            @RequestParam(required = false) BigDecimal maxTotal,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to
    ) {

        if (minTotal != null && minTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("minTotal must be >= 0");
        }

        if (maxTotal != null && maxTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("maxTotal must be >= 0");
        }

        if (minTotal != null && maxTotal != null && minTotal.compareTo(maxTotal) > 0) {
            throw new IllegalArgumentException("minTotal cannot be greater than maxTotal");
        }

        if (from != null && to != null && from.isAfter(to)) {
            throw new IllegalArgumentException("from must be before or equal to to");
        }

        return service.findAll(
                q,
                status,
                currency,
                minTotal,
                maxTotal,
                from,
                to
        );
    }





}
