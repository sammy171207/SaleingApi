package com.example.Agent.service;

import com.example.Agent.modal.Product;
import com.example.Agent.request.ProductCreateRequest;

import java.util.List;

public interface ProductService {
    List<Product>getAllProducts() throws Exception;

    Product getProductById(Long id) throws Exception;

    Product addProduct(ProductCreateRequest request) throws Exception;

    void deleteProduct(Long id) throws Exception;

    List<Product>searchProductByName(String name) throws Exception;

    Product updateProduct(Long id,ProductCreateRequest request)throws Exception;

    Product updateAvailabilityById(Long id, boolean available) throws Exception;
}
