package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import com.ecom.Ecommerce.payloads.ShipmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryBoyService {
    public DeliveryBoyDto addDeliverBoy(DeliveryBoyDto deliveryBoyDto);
    public DeliveryBoyDto updateDeliveryBoy(DeliveryBoyDto deliveryBoyDto,int id);
    public List<ShipmentDto> getAllShipments(int deliveryBoyId);
    public String deleteDeliverBoy(String email);
    public List<DeliveryBoyDto> getAllDeliverBoy();
    public DeliveryBoyDto getDeliveryBoyByEmail(String email);
    public String successfulDelivery(int shipmentId,int deliveryByoId);
    public String orderCancelation(int shipmentId,int deliveryByoId);


}
