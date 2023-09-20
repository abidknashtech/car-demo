package com.nashtech.inventory.query;

import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductsQueryHandler {

	private final ProductsRepository productsRepository;

	public ProductsQueryHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@QueryHandler
	public List<ProductsSummary> findProducts(FindProductsQuery query) {
		List<ProductsSummary> productsRest = new ArrayList<>();
		for(ProductEntity productEntity: productsRepository.findAll()) {
			ProductsSummary productRequest = new ProductsSummary();
			BeanUtils.copyProperties(productEntity, productRequest);
			productsRest.add(productRequest);
		}
		return productsRest;

	}

}
