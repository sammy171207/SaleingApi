package com.example.Agent.controller;

import com.example.Agent.modal.Product;
import com.example.Agent.modal.User;
import com.example.Agent.request.ProductCreateRequest;
import com.example.Agent.response.MessageResponse;
import com.example.Agent.service.ProductService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductCreateRequest request, @RequestHeader("Authorization")String jwt)throws Exception{
        User user= userService.findUserByJwtToken(jwt);
        if(user ==null ){
            throw  new Exception("Invalid Jwt Token or user not found");
        }
        Product product=productService.addProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/details")
    public ResponseEntity<Product>updateProduct(@PathVariable Long productId,@RequestBody ProductCreateRequest request,@RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        if(user==null){
            throw new Exception("JWT token or user not found");
        }
        Product updatedProduct=productService.updateProduct(productId,request);
        if(updatedProduct !=null){
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<MessageResponse>deleteProduct(@PathVariable Long productId,@RequestHeader("Authorization")String jwt)throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        MessageResponse response = new MessageResponse();
        if (user == null) {
            throw new Exception("Invalid JWT token or user not found");
        }
        if (!userService.isAdmin(user)) {
            response.setMessage("authrize user");
            return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }
        productService.deleteProduct(productId);
        response.setMessage("Deleted Succesfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productId}/availability")
    public ResponseEntity<Product>updateProductAvaibilityStatus(@PathVariable Long productId,  @RequestParam boolean available,@RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Product product=productService.updateAvailabilityById(productId,available);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
