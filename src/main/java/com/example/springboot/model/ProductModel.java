package com.example.springboot.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity //Transform the class into a Entity in the application
@Table(name = "TB-PRODUCTS") //Give the name of the table in the database
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id //Make the UUID the Id of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // This notation make the ID geration automatically
    private UUID idProduct; //UUID is a type of an ID for microservices application it helps to not have equal ID
    private String name;
    private BigDecimal value;

    //Getters and Setters of the entitys
    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
