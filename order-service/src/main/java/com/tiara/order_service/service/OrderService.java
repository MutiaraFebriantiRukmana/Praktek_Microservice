package com.tiara.order_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.tiara.order_service.model.Order;
import com.tiara.order_service.repository.OrderRepository;
import com.tiara.order_service.vo.Pelanggan;
import com.tiara.order_service.vo.Produk;
import com.tiara.order_service.vo.ResponseTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllorders(){
    return orderRepository.findAll();
    }

    public Order getorderById(Long id) {
    return orderRepository.findById(id).orElse(null);
    }

    public Order createorder(Order order){
    return orderRepository.save(order);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Order order = getorderById(id);
        Produk produk = restTemplate.getForObject("http://localhost:8081/api/prpduk"+ order.getProdukId(), Produk.class);
        Pelanggan pelanggan = restTemplate.getForObject("http://localhost:8082/api/pelanggan" + order.getPelangganId(), Pelanggan.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);
        responseList.add(vo);
        return responseList;
    }


    public void deleteorder (Long id){
    orderRepository.deleteById(id);
    }
}


