package com.kata.delivery.service;

import com.kata.delivery.enumiration.DeliveryMode;
import com.kata.delivery.model.DeliverySlot;

import java.util.List;

public interface DeliveryService {

    List<DeliverySlot> getAvailableDeliverySlots(DeliveryMode modeDelivery);

    List<DeliverySlot> getAllDeliverySlots(DeliveryMode modeDelivery);

    boolean reserveDeliverySlot(DeliveryMode modeDelivery, int slotIndex);

    List<DeliveryMode> getDeliveryMode();
}
