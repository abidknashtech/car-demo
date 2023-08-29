package com.nashtech.order.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

import javax.annotation.Nonnull;

public class OrderServiceEventsErrorHandler implements ListenerInvocationErrorHandler {

	@Override
	public void onError(Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {
		throw exception;
	}

}