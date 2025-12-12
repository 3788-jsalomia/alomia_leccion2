package org.example.alomia_leccion2.services;

import org.example.alomia_leccion2.exceptions.DuplicateResourceException;
import org.example.alomia_leccion2.models.DTO.PurchaseOrderRequestDTO;
import org.example.alomia_leccion2.models.DTO.PurchaseOrderResponseDTO;
import org.example.alomia_leccion2.models.entities.OrderStatus;
import org.example.alomia_leccion2.models.entities.Currency;
import org.example.alomia_leccion2.models.entities.PurchaseOrder;
import org.example.alomia_leccion2.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseOrderResponseDTO save(PurchaseOrderRequestDTO request) {

        if (repository.existsByOrderNumber(request.getOrderNumber())) {
            throw new DuplicateResourceException(
                    "Order number already exists: " + request.getOrderNumber()
            );
        }

        PurchaseOrder po = new PurchaseOrder();
        po.setOrderNumber(request.getOrderNumber());
        po.setSupplierName(request.getSupplierName());
        po.setStatus(request.getStatus());
        po.setTotalAmount(request.getTotalAmount());
        po.setCurrency(request.getCurrency());
        po.setCreatedAt(LocalDateTime.now());


        PurchaseOrder saved = repository.save(po);

        return mapToDTO(saved);
    }


    @Override
    public List<PurchaseOrderResponseDTO> findAll(
            String q,
            OrderStatus status,
            Currency currency,
            BigDecimal minTotal,
            BigDecimal maxTotal,
            LocalDateTime from,
            LocalDateTime to
    ) {

        return repository.findByFilters(
                        q,
                        status,
                        currency,
                        minTotal,
                        maxTotal,
                        from,
                        to
                )
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }






    private PurchaseOrderResponseDTO mapToDTO(PurchaseOrder po) {
        PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
        dto.setId(po.getId());
        dto.setOrderNumber(po.getOrderNumber());
        dto.setSupplierName(po.getSupplierName());
        dto.setStatus(po.getStatus());
        dto.setTotalAmount(po.getTotalAmount());
        dto.setCurrency(po.getCurrency());
        dto.setCreatedAt(po.getCreatedAt());
        return dto;
    }
}
