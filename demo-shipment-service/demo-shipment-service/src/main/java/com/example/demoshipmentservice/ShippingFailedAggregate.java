package com.example.demoshipmentservice;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.democore.CreateShippingFailedCommand;
import com.example.democore.OrderShippedEvent;
import com.example.democore.OrderShippedFailedEvent;

@Aggregate
public class ShippingFailedAggregate {

	@AggregateIdentifier
	private String shippingIdd;

	private String orderId;

	private String paymentId;

	private String userToken;

	private boolean isOrderShipped;

	public ShippingFailedAggregate() {
	}

	@CommandHandler
	public ShippingFailedAggregate(CreateShippingFailedCommand createShippingFailedCommand) {
		System.out.println("CreateShippingFailedCommand :: " + createShippingFailedCommand.shippingId);
		AggregateLifecycle.apply(
				new OrderShippedFailedEvent(createShippingFailedCommand.shippingId, createShippingFailedCommand.orderId,
						createShippingFailedCommand.paymentId, createShippingFailedCommand.userToken));
	}

	@EventSourcingHandler
	protected void on(OrderShippedFailedEvent orderShippedFailedEvent) {
		System.out.println("OrderShippedEvent :: " + orderShippedFailedEvent.shippingId);
		// String res = shippingServiceImpl.performShippingBussLogic();
		// if (res.equals("done")) {
		// isOrderShipped=true;
		// }

		this.shippingIdd = orderShippedFailedEvent.shippingId;
		this.orderId = orderShippedFailedEvent.orderId;
		this.userToken = orderShippedFailedEvent.userToken;
	}

}
