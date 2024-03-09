package com.kata.delivery.controller;

import com.kata.delivery.model.DeliveryModeResource;
import com.kata.delivery.model.DeliverySlot;
import com.kata.delivery.model.ReserveSlotDTO;
import com.kata.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import com.kata.delivery.enumiration.DeliveryMode;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/delivery-mode")
    @Operation(summary = "Get all Delivery mode")
    public CollectionModel<DeliveryModeResource> getAllDeliveryMode() {
        List<DeliveryModeResource> deliveryModeResources = new ArrayList<>();
        for (DeliveryMode mode : DeliveryMode.values()) {
            DeliveryModeResource modeResource = new DeliveryModeResource(mode);
            Link selfLink = WebMvcLinkBuilder.linkTo(DeliveryController.class)
                    .slash("delivery-slots")
                    .slash(mode.name())
                    .withRel("delivery-slots");
            modeResource.add(selfLink);
            deliveryModeResources.add(modeResource);
        }
        Link selfLink = WebMvcLinkBuilder.linkTo(DeliveryController.class).slash("delivery-mode").withSelfRel();
        return CollectionModel.of(deliveryModeResources, selfLink);
    }
    @GetMapping("/delivery-slots/{id}")
    @Operation(summary = "Get all Delivery slots by mode")
    public CollectionModel<DeliverySlot> getAllDeliverySlots(@PathVariable("id") String modeDelivery) {
        List<DeliverySlot> deliverySlots = deliveryService.getAvailableDeliverySlots(DeliveryMode.valueOf(modeDelivery));
        Link selfLink = WebMvcLinkBuilder.linkTo(DeliveryController.class)
                .slash("delivery-slots")
                .slash(modeDelivery)
                .withSelfRel();
        return CollectionModel.of(deliverySlots, selfLink);
    }

    @GetMapping("/available-delivery-slots/{id}")
    @Operation(summary = "Get all Available Delivery slots by mode")
    public CollectionModel<DeliverySlot> getAvailableDeliverySlots(@PathVariable("id") String modeDelivery) {
        List<DeliverySlot> deliverySlots = deliveryService.getAvailableDeliverySlots(DeliveryMode.valueOf(modeDelivery));
        Link selfLink = WebMvcLinkBuilder.linkTo(DeliveryController.class)
                .slash("available-delivery-slots")
                .slash(modeDelivery)
                .withSelfRel();
        return CollectionModel.of(deliverySlots, selfLink);
    }

    @PostMapping("/reserve-delivery-slot")
    @Operation(summary = "Register delivery by slot index and delivery mode")
    public String reserveDeliverySlot(@RequestBody ReserveSlotDTO reserveSlotDTO) {
        boolean reserved = deliveryService.reserveDeliverySlot(reserveSlotDTO.getDeliveryMode(), reserveSlotDTO.getIndex());
        if (reserved) {
            return "Delivery slot reserved successfully";
        } else {
            return "Failed to reserve delivery slot";
        }
    }
}
