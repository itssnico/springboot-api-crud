package com.example.springboot.controller;

import com.example.springboot.dtos.ProductRecordDTO;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;
import jakarta.validation.Valid;  //the @Valid annotation makes possible to get the ingo in the productRecordDTO
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController //this notation shows the application that this class is its controller
public class ProductController {
    @Autowired //this is a repository injection point
    ProductRepository productRepository;

    @PostMapping("/products")         //name of the method
    public ResponseEntity<ProductModel> saveProduct (@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel); //convert the DTO to model
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
        //the .status(HttpStatus.CREATED) - show the user that the data was successfully received (201)
        //.body(productRepository.save(productModel)) - insert into the database the new data
    }

    @GetMapping("/products")             //name of the method
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productsList = productRepository.findAll(); //we receive the result of the query in the productList object
        if(!productsList.isEmpty()){
            for(ProductModel product : productsList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
            // linkTo send to the product we click on
            // methodOn show where we getting the product
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
        //the .status(HttpStatus.OK) - show that the request data was successfully get (200)
        //.body(productRepository.findAll()) - get all the data in the database
    }

    @GetMapping("/products/{id}")//name of the method
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id")UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){ //if the optional product is not in the database will return an error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
        //@PathVariable we use the exact id to return the item
        //the .status(HttpStatus.OK) - show the product that the we searched was successfully get (200)
        //.body(productRepository.findAll()) - get the product by his Id data in the database
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDTO productRecordDTO){
     Optional<ProductModel> productO = productRepository.findById(id);
     if(productO.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
     }
     var productModel = productO.get();
     BeanUtils.copyProperties(productRecordDTO, productModel);
     return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
    //@PathVariable we use the exact id to return the item | @ResquestBody to send the new data
    //BeanUtil transform the DTO to productModel
    //the .status(HttpStatus.OK) - show the product that the we searched was successfully get (200)
    //.body(productRepository.findAll()) - get the product by his Id data in the database

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }
    //@PathVariable we use the exact id to return the item
    //the .status(HttpStatus.OK) - show the product that the we searched was successfully deleted
}
