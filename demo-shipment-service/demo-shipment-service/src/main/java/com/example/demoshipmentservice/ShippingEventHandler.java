package com.example.demoshipmentservice;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.example.democore.InitiateOrderShippedEvent;

@Service
public class ShippingEventHandler {

	@EventHandler
	public void on(InitiateOrderShippedEvent initiateOrderShippedEvent) {

		System.out.println("InitiateOrderShippedEvent :: " + initiateOrderShippedEvent.shippingId);

	}

}
