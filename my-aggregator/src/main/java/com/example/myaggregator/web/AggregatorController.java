package com.example.myaggregator.web;

import com.example.myaggregator.model.FullOrderData;
import com.example.myaggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/aggregate")
public class AggregatorController {
    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/{id}")
    public FullOrderData aggregate(@PathVariable Long id) {
        return aggregatorService.getFullOrderInformation(id);
    }
}