package com.nashtech.order.commands;

import com.nashtech.order.exception.UserNotFoundException;
import com.nashtech.order.repository.entity.OrderLookup;
import com.nashtech.order.repository.OrderLookupRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateOrderCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	private final OrderLookupRepository orderLookupRepository;
	
	public CreateOrderCommandInterceptor(OrderLookupRepository productLookupRepository) {
		this.orderLookupRepository = productLookupRepository;
	}
 
	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
		 
		return (index, command) -> {
			
			log.info("Intercepted command: " + command.getPayloadType());
			
			if(CreateOrderCommand.class.equals(command.getPayloadType())) {
				String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
				CreateOrderCommand createOrderCommand = (CreateOrderCommand)command.getPayload();
				if (!userId.equals(createOrderCommand.getUserId())) {
					throw new UserNotFoundException(
							String.format("User [%s] does not exist", createOrderCommand.getUserId()));
				}
				Optional<OrderLookup> orderLookup =  orderLookupRepository.findById(createOrderCommand.getOrderId());
				
				if(orderLookup.isPresent()) {
					throw new IllegalStateException(
							String.format("Car with orderId %s or carId %s already exist",
									createOrderCommand.getOrderId(), createOrderCommand.getCarId()));
				}

			}
			
			return command;
		};
	}

}