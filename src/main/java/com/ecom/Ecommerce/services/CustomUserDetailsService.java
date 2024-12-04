package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.entities.DeliveryBoy;
import com.ecom.Ecommerce.entities.Merchant;
import com.ecom.Ecommerce.repo.CustomerRepo;
import com.ecom.Ecommerce.repo.DeliveryBoyRepo;
import com.ecom.Ecommerce.repo.MerchantRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final JwtService jwtService;
    private final CustomerRepo customerRepo;
    private final MerchantRepo merchantRepo;
    private final DeliveryBoyRepo deliveryBoyRepo;

    public CustomUserDetailsService(JwtService jwtService, CustomerRepo customerRepo, MerchantRepo merchantRepo, DeliveryBoyRepo deliveryBoyRepo) {
        this.jwtService = jwtService;
        this.customerRepo = customerRepo;
        this.merchantRepo = merchantRepo;
        this.deliveryBoyRepo = deliveryBoyRepo;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    /*        String role = jwtService.extractRole(username);

        if ("CUSTOMER".equals(role)) {
            return loadCustomerDetails(username);
        } else if ("MERCHANT".equals(role)) {
            return loadMerchantDetails(username);
        } else if ("DELIVERY".equals(role)) {
            return loadDeliveryDetails(username);
        } else {
            throw new UsernameNotFoundException("Invalid role or type.");
        }*/
        Optional<Customer> customer = customerRepo.findByEmail(email);

        if (customer.isPresent()) {
            return new User(
                    customer.get().getEmail(),
                    customer.get().getPassword(),
                    AuthorityUtils.createAuthorityList(customer.get().getRoles())
            );
        }

        Optional<Merchant> merchant = merchantRepo.findByEmail(email);

        if (merchant.isPresent()) {
            return new User(
                    merchant.get().getEmail(),
                    merchant.get().getPassword(),
                    AuthorityUtils.createAuthorityList(merchant.get().getRoles())
            );
        }

        Optional<DeliveryBoy> deliveryBoy = deliveryBoyRepo.findByEmail(email);

        if (deliveryBoy.isPresent()) {
            return new User(
                    deliveryBoy.get().getEmail(),
                    deliveryBoy.get().getPassword(),
                    AuthorityUtils.createAuthorityList(deliveryBoy.get().getRoles())
            );
        }
        throw new UsernameNotFoundException("User not found for email: " + email);
    }

   /* private UserDetails loadCustomerDetails(String email) {

        Optional<Customer> customer = customerRepo.findByEmail(email);

        if(customer.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }

        return new User(
                customer.get().getEmail(),
                customer.get().getPassword(),
                AuthorityUtils.createAuthorityList(customer.get().getRoles())
        );
    }

    private UserDetails loadMerchantDetails(String email) {
        Optional<Merchant> merchant = merchantRepo.findByEmail(email);

        if(merchant.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }

        return new User(
                merchant.get().getEmail(),
                merchant.get().getPassword(),
                AuthorityUtils.createAuthorityList(merchant.get().getRoles())
        );
    }

    private UserDetails loadDeliveryDetails(String email) {
        Optional<DeliveryBoy> deliveryBoy = deliveryBoyRepo.findByEmail(email);

        if(deliveryBoy.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }

        return new User(
                deliveryBoy.get().getEmail(),
                deliveryBoy.get().getPassword(),
                AuthorityUtils.createAuthorityList(deliveryBoy.get().getRoles())
        );
    }*/

}