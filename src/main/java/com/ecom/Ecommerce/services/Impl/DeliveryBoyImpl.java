package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.constants.Constant;
import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.entities.DeliveryBoy;
import com.ecom.Ecommerce.entities.OrderedProd;
import com.ecom.Ecommerce.entities.Shipment;
import com.ecom.Ecommerce.payloads.CustomerDtoShipment;
import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import com.ecom.Ecommerce.payloads.ShipmentDto;
import com.ecom.Ecommerce.repo.CustomerRepo;
import com.ecom.Ecommerce.repo.DeliveryBoyRepo;
import com.ecom.Ecommerce.repo.OrderedProdRepo;
import com.ecom.Ecommerce.repo.ShipmentRepo;
import com.ecom.Ecommerce.services.DeliveryBoyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryBoyImpl implements DeliveryBoyService {

    @Autowired
    DeliveryBoyRepo deliveryBoyRepo;

    @Autowired
    ShipmentRepo shipmentRepo;

    @Autowired
    OrderedProdRepo orderedProdRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public DeliveryBoyDto addDeliverBoy(DeliveryBoyDto deliveryBoyDto) {
        DeliveryBoy deliveryBoy=new DeliveryBoy();
        BeanUtils.copyProperties(deliveryBoyDto,deliveryBoy);
        deliveryBoyRepo.save(deliveryBoy);
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }

    @Override
    public DeliveryBoyDto updateDeliveryBoy(DeliveryBoyDto deliveryBoyDto, int id) {
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("deliveryBoy","deliveryBoyId" ,id)
        );
        deliveryBoy.setMobile(deliveryBoy.getMobile());
        deliveryBoy.setEmail(deliveryBoy.getEmail());
        deliveryBoy.setName(deliveryBoyDto.getName());
        deliveryBoyRepo.save(deliveryBoy);
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }

    @Override
    public String deleteDeliverBoy(String email) {
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("deliveryBoy","email " +email,0)
        );
        deliveryBoyRepo.delete(deliveryBoy);
        return "Delivery Boy deleted";
    }

    @Override
    public List<DeliveryBoyDto> getAllDeliverBoy() {
        List<DeliveryBoy> deliveryBoyList=deliveryBoyRepo.findAll();
        List<DeliveryBoyDto> deliveryBoyDtoList=new ArrayList<>();
        for(DeliveryBoy deliveryBoy:deliveryBoyList){
            DeliveryBoyDto deliveryBoyDto=new DeliveryBoyDto();
            BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
            deliveryBoyDtoList.add(deliveryBoyDto);
        }
        return deliveryBoyDtoList;
    }

    @Override
    public DeliveryBoyDto getDeliveryBoyByEmail(String email) {
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("customer","email " +email,0)
        );
        DeliveryBoyDto deliveryBoyDto=new DeliveryBoyDto();
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }



    @Override
    public List<ShipmentDto> getAllShipments(int deliveryBoyId){
        List<Shipment> shipment= shipmentRepo.findAll();


        return shipment.stream().map(
                ship->{
                    ShipmentDto shipmentDto=new ShipmentDto();
                    Customer customer=customerRepo.findById(ship.getCustomerId()).orElseThrow(
                            ()->new ResourceNotFoundException("customer","customerId ",ship.getCustomerId())
                    );
                    CustomerDtoShipment customerDtoShipment=new CustomerDtoShipment();
                    BeanUtils.copyProperties(customer,customerDtoShipment);
                    shipmentDto.setCustomerDtoShipment(customerDtoShipment);
                    BeanUtils.copyProperties(ship,shipmentDto);
                    return shipmentDto;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public String successfulDelivery(int shipmentId, int deliveryByoId) {
        Shipment shipment=shipmentRepo.findById(shipmentId).orElseThrow(
                ()->new ResourceNotFoundException("shipment","shipmentId ",shipmentId)
        );
        shipment.setStatus(Constant.delivered);
        shipmentRepo.save(shipment);
        OrderedProd orderedProd= orderedProdRepo.findById(shipment.getOrderId()).orElseThrow(
                ()->new ResourceNotFoundException("orderedPro","OrderId",shipment.getOrderId())
        );
        orderedProd.setStatus(Constant.delivered);
        orderedProdRepo.save(orderedProd);
        return "Delivered Order";
    }

    @Override
    public String orderCancelation(int shipmentId,int deliveryByoId) {
        Shipment shipment=shipmentRepo.findById(shipmentId).orElseThrow();
        shipment.setStatus(Constant.cancel);
        shipmentRepo.save(shipment);
        OrderedProd orderedProd= orderedProdRepo.findById(shipment.getOrderId()).orElseThrow(
                ()->new ResourceNotFoundException("orderedPro","OrderId",shipment.getOrderId())
        );
        orderedProd.setStatus(Constant.cancel);
        orderedProdRepo.save(orderedProd);
        return "Order Canceled";
    }


}
