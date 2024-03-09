package com.kata.delivery.service.impl;

import com.kata.delivery.enumiration.DeliveryMode;
import com.kata.delivery.model.DeliverySlot;
import com.kata.delivery.service.DeliveryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImp implements DeliveryService {
    private Map<DeliveryMode, List<DeliverySlot>> deliverySlots;

    public DeliveryServiceImp() {
        // Initialize delivery slots
        this.deliverySlots = new HashMap<>();
        for (DeliveryMode mode : DeliveryMode.values()) {
            this.deliverySlots.put(mode, new ArrayList<>());
        }

        // Example: Add sample delivery slots for each mode
        for (DeliveryMode mode : DeliveryMode.values()) {
            List<DeliverySlot> slots = this.deliverySlots.get(mode);

            for (int i = 0; i <= 5; i++) {
                LocalDateTime startTime = LocalDateTime.now().plusHours(i);
                LocalDateTime endTime = startTime.plusHours(1);
                slots.add(new DeliverySlot(startTime, endTime));
            }
        }
    }

    @Override
    public List<DeliveryMode> getDeliveryMode() {
        return Arrays.stream(DeliveryMode.values()).toList();
    }
    @Override
    public List<DeliverySlot> getAvailableDeliverySlots(DeliveryMode modeDelivery) {
        return this.deliverySlots.get(modeDelivery).stream().filter(item->!item.isReserved()).collect(Collectors.toList());
    }

    @Override
    public List<DeliverySlot> getAllDeliverySlots(DeliveryMode modeDelivery) {
        return this.deliverySlots.get(modeDelivery);
    }

    @Override
    public boolean reserveDeliverySlot(DeliveryMode modeDelivery, int slotIndex) {
        // Check if the delivery slot is available and reserve it
        List<DeliverySlot> slots = this.deliverySlots.get(modeDelivery);
        if (slotIndex >= 0 && slotIndex < slots.size()) {
            DeliverySlot slot = slots.get(slotIndex);
            if (!slot.isReserved()) {
                slot.setReserved(true);
                return true; // Reservation successful
            }
        }
        return false;
    }
}
