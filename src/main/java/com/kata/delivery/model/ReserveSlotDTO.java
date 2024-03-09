package com.kata.delivery.model;

import com.kata.delivery.enumiration.DeliveryMode;
import lombok.Data;

@Data
public class ReserveSlotDTO {
    private DeliveryMode deliveryMode;
    private int index;
}
