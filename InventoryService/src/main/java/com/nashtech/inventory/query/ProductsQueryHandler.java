package com.nashtech.inventory.query;

import java.util.ArrayList;
import java.util.List;

import com.nashtech.inventory.core.data.Product;
import com.nashtech.inventory.core.data.ProductsRepository;
import com.nashtech.inventory.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class ProductsQueryHandler {

	private final ProductsRepository productsRepository;

	public ProductsQueryHandler(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}

	@QueryHandler
	public List<ProductRestModel> findProducts(FindProductsQuery query) {

		List<ProductRestModel> productsRest = new ArrayList<>();

		List<Product> storedProducts =  productsRepository.findAll();

		for(Product productEntity: storedProducts) {
			ProductRestModel productRestModel = new ProductRestModel();
			BeanUtils.copyProperties(productEntity, productRestModel);
			productsRest.add(productRestModel);
		}

		return productsRest;

	}

}
