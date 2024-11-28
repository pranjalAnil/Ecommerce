package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryBoyService {
    public DeliveryBoyDto addDeliverBoy(DeliveryBoyDto deliveryBoyDto);
    public DeliveryBoyDto updateDeliveryBoy(DeliveryBoyDto deliveryBoyDto,int id);
    public String deleteDeliverBoy(String email);
    public List<DeliveryBoyDto> getAllDeliverBoy();
    public DeliveryBoyDto getDeliveryBoyByEmail(String email);

}
