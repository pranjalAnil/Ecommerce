    package com.ecom.Ecommerce.services.Impl;
    import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
    import com.ecom.Ecommerce.entities.Cart;
    import com.ecom.Ecommerce.entities.Customer;
    import com.ecom.Ecommerce.entities.Products;
    import com.ecom.Ecommerce.payloads.CartDto;
    import com.ecom.Ecommerce.payloads.ProductsDto;
    import com.ecom.Ecommerce.repo.CartRepo;
    import com.ecom.Ecommerce.repo.CustomerRepo;
    import com.ecom.Ecommerce.repo.ProductRepo;
    import com.ecom.Ecommerce.services.CartService;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;
    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class CartServiceImpl implements CartService {

        @Autowired
        CartRepo cartRepo;

        @Autowired
        ProductRepo productRepo;

        @Autowired
        CustomerRepo customerRepo;

        @Override
        public CartDto addToCart(int prodId) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email =  authentication.getName();
            Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("customer","email " +email,0));

            System.out.println("Received customerId: " + customer.getCustomerId());
            Cart cart = new Cart();
            CartDto cartDto = new CartDto();
            ProductsDto productsDto = new ProductsDto();
            Products products = productRepo.findById(prodId).orElseThrow(
                    ()->new ResourceNotFoundException("Product","productId",prodId)
            );
            BeanUtils.copyProperties(products, productsDto);
            cartDto.setProductsDto(productsDto);
            System.out.println(products);
          /*  Customer customer = customerRepo.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("customer","customerID",prodId)
            );*/
            cartDto.setCustomerId(customer.getCustomerId());
            System.out.println(cartDto.getCustomerId());
            BeanUtils.copyProperties(cartDto, cart);
            cart.setProductId(products.getProdId());
            cartRepo.save(cart);
            return cartDto;
        }

        @Override
        public List<CartDto> getAllCart() {
            List<Cart> list = cartRepo.findAll();
            List<CartDto> list1 = new ArrayList<>();
            for (Cart cart : list){
                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(cart, cartDto);
                list1.add(cartDto);
            }
            return list1;
        }

        @Override
        public List<CartDto> getCart() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email =  authentication.getName();
            Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("customer","email " +email,0));

            System.out.println("Received customerId: " + customer.getCustomerId());
            ProductsDto productsDto = new ProductsDto();
            List<Cart> list = cartRepo.findByCustomerId(customer.getCustomerId());
            System.out.println(list);
            List<CartDto> list1 = new ArrayList<>();
            for (Cart cart : list){
               Products products1 = productRepo.findById(cart.getProductId()).orElseThrow(
                      ()->new ResourceNotFoundException("customer","customerID",cart.getProductId())
               );
                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(products1, productsDto);
                cartDto.setProductsDto(productsDto);
                BeanUtils.copyProperties(cart, cartDto);
                list1.add(cartDto);
            }
            return list1;
        }

        @Override
        public String removeFromCart(int cartId) {
            cartRepo.deleteById(cartId);
            return "Product removed" ;
        }

        @Override
        public CartDto orderProduct(int proId) {
            return null;
        }
    }
