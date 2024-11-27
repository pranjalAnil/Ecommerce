package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.repo.CustomerRepo;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public CustomerDto createAcc(CustomerDto customerDto) {
        Customer customer =new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        customerRepo.save(customer);
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    @Override
    public CustomerDto updateAcc(CustomerDto customerDto,String email) {
        Customer customer= customerRepo.findByEmail(email).orElseThrow();
        customer.setCustomerName(customerDto.getCustomerName());
        if (!Objects.equals(customerDto.getEmail(), email) || customerDto.getEmail() != null) {
            customer.setEmail(customerDto.getEmail());
        }
        customer.setMobile(customerDto.getMobile());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customer.getPassword());
        customerRepo.save(customer);
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    @Override
    public String deleteAccount(String email) {
        customerRepo.delete(customerRepo.findByEmail(email).orElseThrow());
        return "Account Deleted";
    }
}
