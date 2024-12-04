package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import com.ecom.Ecommerce.payloads.ShipmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeliveryBoyService {
    public DeliveryBoyDto addDeliverBoy(DeliveryBoyDto deliveryBoyDto);
    public DeliveryBoyDto updateDeliveryBoy(DeliveryBoyDto deliveryBoyDto);
    public List<ShipmentDto> getAllShipments();
    public String deleteDeliverBoy();
    public List<DeliveryBoyDto> getAllDeliverBoy();
    public DeliveryBoyDto getDeliveryBoyByEmail();
    public String successfulDelivery(int shipmentId);
    public String orderCancelation(int shipmentId);


}
