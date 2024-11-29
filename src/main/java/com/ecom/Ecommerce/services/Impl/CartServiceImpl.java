    package com.ecom.Ecommerce.services.Impl;

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
        public CartDto addToCart(int customerId, int prodId) {
            System.out.println("Received customerId: " + customerId);
            Cart cart = new Cart();
            CartDto cartDto = new CartDto();
            ProductsDto productsDto = new ProductsDto();
            Products products = productRepo.findById(prodId).orElseThrow();
            BeanUtils.copyProperties(products, productsDto);
            cartDto.setProductsDto(productsDto);
            System.out.println(products);
            Customer customer = customerRepo.findById(customerId).orElseThrow();
          //  cart.setCustomer(customer);
            if (customer != null) {
                cartDto.setCustomerId(customer.getCustomerId());
                System.out.println(cartDto.getCustomerId());
            }
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
        public List<CartDto> getCart(int customerId) {
            System.out.println("Received customerId: " + customerId);
            ProductsDto productsDto = new ProductsDto();
        //    Customer customer = customerRepo.findById(customerId).orElseThrow();
            List<Cart> list = cartRepo.findByCustomerId(customerId);
            System.out.println(list);
            List<CartDto> list1 = new ArrayList<>();
            for (Cart cart : list){
                if (cart.getProductId() == 0) {
                    // Skip cart entries with invalid productId
                    continue;
                }
               Products products1 = productRepo.findById(cart.getProductId()).orElseThrow();
                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(products1, productsDto);
                cartDto.setProductsDto(productsDto);
                BeanUtils.copyProperties(cart, cartDto);
                list1.add(cartDto);
            }
            return list1;
        }

        @Override
        public CartDto removeFromCart(int prodId) {
            return null;
        }

        @Override
        public CartDto orderProduct(int proId) {
            return null;
        }
    }
