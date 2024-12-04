package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.entities.DeliveryBoy;
import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import com.ecom.Ecommerce.repo.DeliveryBoyRepo;
import com.ecom.Ecommerce.services.DeliveryBoyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeliveryBoyImpl implements DeliveryBoyService {

    @Autowired
    DeliveryBoyRepo deliveryBoyRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public DeliveryBoyDto addDeliverBoy(DeliveryBoyDto deliveryBoyDto) {
        DeliveryBoy deliveryBoy=new DeliveryBoy();
        BeanUtils.copyProperties(deliveryBoyDto,deliveryBoy);
        deliveryBoy.setPassword(encoder.encode(deliveryBoy.getPassword()));
        deliveryBoy.setRoles(Arrays.asList("ROLE_DELIVERY"));
        deliveryBoyRepo.save(deliveryBoy);
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }

    @Override
    public DeliveryBoyDto updateDeliveryBoy(DeliveryBoyDto deliveryBoyDto, int id) {
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findById(id).orElseThrow();
        deliveryBoy.setMobile(deliveryBoy.getMobile());
        deliveryBoy.setEmail(deliveryBoy.getEmail());
        deliveryBoy.setName(deliveryBoyDto.getName());
        deliveryBoyRepo.save(deliveryBoy);
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }

    @Override
    public String deleteDeliverBoy(String email) {
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findByEmail(email).orElseThrow();
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
        DeliveryBoy deliveryBoy=deliveryBoyRepo.findByEmail(email).orElseThrow();
        DeliveryBoyDto deliveryBoyDto=new DeliveryBoyDto();
        BeanUtils.copyProperties(deliveryBoy,deliveryBoyDto);
        return deliveryBoyDto;
    }
}
