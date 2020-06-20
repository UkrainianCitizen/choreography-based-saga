package ua.citizen.productservice.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private int price;
    private int count;

    public Product(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }
}
