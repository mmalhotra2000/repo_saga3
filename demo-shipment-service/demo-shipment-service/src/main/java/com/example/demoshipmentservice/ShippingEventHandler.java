package com.example.demoshipmentservice;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.example.democore.CreateShippingFailedCommand;
import com.example.democore.InitiateOrderShippedEvent;
import com.example.democore.OrderShippedEvent;

@Service
public class ShippingEventHandler {

	private final CommandGateway commandGateway;

	public ShippingEventHandler(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@EventHandler
	public void on(InitiateOrderShippedEvent initiateOrderShippedEvent) {

		System.out.println("ShippingEventHandler ,OrderShippedEvent :: " + initiateOrderShippedEvent.shippingId);
		commandGateway.send(new CreateShippingFailedCommand(initiateOrderShippedEvent.shippingId, initiateOrderShippedEvent.orderId,
				initiateOrderShippedEvent.paymentId, initiateOrderShippedEvent.userToken));

	}

}
