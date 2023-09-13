package com.nashtech.inventory.restapi;

import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.query.FindProductsQuery;
import com.nashtech.inventory.query.ProductsSummary;
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
    public List<ProductsSummary> getProducts() {
        return queryGateway.query(new FindProductsQuery(), ResponseTypes.multipleInstancesOf(ProductsSummary.class)).join();
    }


    @PostMapping
    public String createProduct(@Valid @RequestBody List<ProductRequest> createProductRequest) {
        createProductRequest.forEach(product->{
            CreateProductCommand createProductCommand = CreateProductCommand.builder()
                    .basePrice(product.getPrice())
                    .quantity(product.getQuantity())
                    .title(product.getTitle())
                    .tax(product.getTax())
                    .productId(UUID.randomUUID().toString()).build();
            commandGateway.send(createProductCommand);
        });

        return "Products added";
    }
}
