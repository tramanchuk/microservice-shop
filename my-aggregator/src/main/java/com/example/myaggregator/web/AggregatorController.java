package com.example.myaggregator.web;

import com.example.myaggregator.model.AggregatedOrder;
import com.example.myaggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/aggregate")
public class AggregatorController {
    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/orders/{id}")
    public AggregatedOrder aggregate(@PathVariable String id) {
        return aggregatorService.getFullOrderInformation(id);
    }
}
