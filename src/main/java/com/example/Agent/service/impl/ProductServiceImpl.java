package com.example.Agent.service.impl;

import com.example.Agent.modal.Product;
import com.example.Agent.repository.ProductRepository;
import com.example.Agent.request.ProductCreateRequest;
import com.example.Agent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        Optional<Product>optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new Exception("product is not Exist");
        }
        return optionalProduct.get();
    }

    @Override
    public Product addProduct(ProductCreateRequest request) throws Exception {
        Product product=new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImages(request.getImages());
        product.setAvailableInStock(request.isAvailableInStock());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> searchProductByName(String name) throws Exception {
        return productRepository.findByName(name);
    }

    @Override
    public Product updateProduct(Long id, ProductCreateRequest request) throws Exception {
        Optional<Product>optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setStockQuantity(request.getStockQuantity());
            product.setImages(request.getImages());
            product.setAvailableInStock(request.isAvailableInStock());
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product updateAvailabilityById(Long id, boolean available) throws Exception {
        Optional<Product>optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            product.setAvailableInStock(available);
            return productRepository.save(product);
        }
        return null;
    }
}
