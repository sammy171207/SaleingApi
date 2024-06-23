package com.example.Agent.service.impl;

import com.example.Agent.modal.Cart;
import com.example.Agent.modal.CartItem;
import com.example.Agent.modal.Product;
import com.example.Agent.modal.User;
import com.example.Agent.repository.CartItemRepository;
import com.example.Agent.repository.CartRepository;
import com.example.Agent.request.AddCartItemRequest;
import com.example.Agent.service.CartService;
import com.example.Agent.service.ProductService;
import com.example.Agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Product product = productService.getProductById(request.getId());

        Cart cart = cartRepository.findByUserId(user.getId());
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().equals(product)) {
                int newQuantity = cartItem.getQuantity() * request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
      CartItem newCartItem=new CartItem();
        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setTotalPrice((long) (request.getQuantity()*product.getPrice()));

        CartItem savedCartItem=cartItemRepository.save(newCartItem);
        cart.getCartItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem>cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item=cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice((long) (item.getProduct().getPrice()*quantity));
        return cartItemRepository.save(item);

    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        return null;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        return null;
    }
}
