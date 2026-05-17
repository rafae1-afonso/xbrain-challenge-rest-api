package xbrain_challenge.rest_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xbrain_challenge.rest_api.database.entity.DeliveryEntity;
import xbrain_challenge.rest_api.dto.DeliveryDto;
import xbrain_challenge.rest_api.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/entregas")
@RequiredArgsConstructor
@Validated
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveryEntity> listAll() {
        return deliveryService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryEntity createOrder(@Valid @RequestBody DeliveryDto deliveryDto) {
        return deliveryService.create(deliveryDto);
    }
}
