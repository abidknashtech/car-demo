package com.nashtech.inventory.command;

import com.nashtech.inventory.core.data.ProductLookupEntity;
import com.nashtech.inventory.core.data.ProductLookupRepository;
import com.nashtech.inventory.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

	private final ProductLookupRepository productLookupRepository;

	public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
		this.productLookupRepository = productLookupRepository;
	}

	@EventHandler
	public void on(ProductCreatedEvent event) {

		ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(),
				event.getTitle());

		productLookupRepository.save(productLookupEntity);

	}


}
