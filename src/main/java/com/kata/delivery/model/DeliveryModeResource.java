package com.kata.delivery.model;

import com.kata.delivery.enumiration.DeliveryMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryModeResource extends RepresentationModel<DeliveryModeResource> {
    private DeliveryMode mode;
}
