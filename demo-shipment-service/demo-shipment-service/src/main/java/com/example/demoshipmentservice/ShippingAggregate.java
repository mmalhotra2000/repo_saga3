package com.example.demoshipmentservice;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.democore.CreateInitiateShippingCommand;
import com.example.democore.InitiateOrderShippedEvent;

@Aggregate
public class ShippingAggregate {

	@AggregateIdentifier
	private String shippingId;

	private String orderId;

	private String paymentId;

	private String userToken;

	private boolean isOrderShipped;

	public ShippingAggregate() {
	}

	@CommandHandler
	public ShippingAggregate(CreateInitiateShippingCommand createInitiateShippingCommand) {
		AggregateLifecycle.apply(new InitiateOrderShippedEvent(createInitiateShippingCommand.shippingId,
				createInitiateShippingCommand.orderId, createInitiateShippingCommand.paymentId,
				createInitiateShippingCommand.userToken));
	}

	@EventSourcingHandler
	protected void on(InitiateOrderShippedEvent InitiateOrderShippedEvent) {
		this.shippingId = InitiateOrderShippedEvent.shippingId;
		this.orderId = InitiateOrderShippedEvent.orderId;
		this.userToken = InitiateOrderShippedEvent.userToken;
	}
}
