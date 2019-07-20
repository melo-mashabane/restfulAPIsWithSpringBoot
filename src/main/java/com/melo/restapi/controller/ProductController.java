package com.melo.restapi.controller;

import com.melo.restapi.model.Product;
import com.melo.restapi.repo.ProductRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/products/")
public class ProductController {

    private ProductRepo productRepo;
    private org.slf4j.Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public void productRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable String id) {
        return productRepo.getOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") String id){
        Product foundProduct = productRepo.getOne(id);
        if(foundProduct != null){
            foundProduct.setName(productToUpdate.getName());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            return productRepo.save(foundProduct);
        } else {
            LOG.info("No products found with given id");
            return productToUpdate;
        }
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable(name = "id") String id){
        Product foundProduct = productRepo.getOne(id);
        if(foundProduct != null){
            productRepo.delete(foundProduct);
        }
    }

}
