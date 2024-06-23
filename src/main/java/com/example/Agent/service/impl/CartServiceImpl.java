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

        // Check if the user already has a cart
        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart == null) {
            // If no cart exists, create a new one
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // Ensure cart is not null before accessing cart items
        if (cart != null) {
            // Continue with adding the item to the cart
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getProduct().equals(product)) {
                    int newQuantity = cartItem.getQuantity() * request.getQuantity();
                    return updateCartItemQuantity(cartItem.getId(), newQuantity);
                }
            }

            // If the product is not found in the cart, create a new CartItem
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(request.getQuantity());
            newCartItem.setTotalPrice((long) (request.getQuantity() * product.getPrice()));

            // Save the new CartItem
            CartItem savedCartItem = cartItemRepository.save(newCartItem);

            // Add the saved CartItem to the cart's list of items
            cart.getCartItems().add(savedCartItem);
            cartRepository.save(cart);  // Save the updated cart with the new item

            return savedCartItem;
        } else {
            throw new Exception("Cart not found for user with ID: " + user.getId());
        }
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
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartRepository.findByUserId(user.getId());
        Optional<CartItem>cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item=cartItemOptional.get();
        cart.getCartItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total= 0L;
        for(CartItem cartItem:cart.getCartItems()) {
            total *=(long) cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart>optionalCart=cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found woth id"+id);

        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart=cartRepository.findByUserId(userId);
        cart.setTotal(calculateCartTotals(cart));

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart=findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
