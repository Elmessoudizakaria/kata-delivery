package com.kata.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "deliverySlots")
public class DeliverySlot extends RepresentationModel<DeliverySlot> {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean reserved;


    public DeliverySlot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}