package com.nashtech.inventory.command.rest;

import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.query.FindProductsQuery;
import com.nashtech.inventory.query.rest.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private QueryGateway queryGateway;
    private final CommandGateway commandGateway;

      public ProductsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @GetMapping("get")
    public List<ProductRestModel> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        return queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
    }


    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();

        String returnValue;

        returnValue = commandGateway.sendAndWait(createProductCommand);


        return returnValue;
    }
}
