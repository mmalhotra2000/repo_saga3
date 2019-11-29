package com.example.demoshipmentservice;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.democore.CreateShippingCommand;
import com.example.democore.InitiateOrderShippedEvent;
import com.example.democore.OrderShippedEvent;

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
	public ShippingAggregate(CreateShippingCommand createShippingCommand) {
		System.out.println("CreateShippingCommand :: "+createShippingCommand.shippingId);
			AggregateLifecycle.apply(new InitiateOrderShippedEvent(createShippingCommand.shippingId,
					createShippingCommand.orderId, createShippingCommand.paymentId, createShippingCommand.userToken));
	}

	@EventSourcingHandler
	protected void on(InitiateOrderShippedEvent orderShippedEvent) {
		System.out.println("OrderShippedEvent :: "+orderShippedEvent.shippingId);
//		String res = shippingServiceImpl.performShippingBussLogic();
//		if (res.equals("done")) {
//			isOrderShipped=true;
//		}

		this.shippingId = orderShippedEvent.shippingId;
		this.orderId = orderShippedEvent.orderId;
		this.userToken = orderShippedEvent.userToken;
	}

}
