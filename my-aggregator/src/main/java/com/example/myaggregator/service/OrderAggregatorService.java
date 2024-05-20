package com.example.myaggregator.service;

import com.example.myaggregator.model.AggregatedOrder;

public interface OrderAggregatorService {
    AggregatedOrder getFullOrderInformation(String orderId);
}
