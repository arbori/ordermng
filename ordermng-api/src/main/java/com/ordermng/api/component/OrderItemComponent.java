package com.ordermng.api.component;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.orderitem.OrderItemBusiness;
import com.ordermng.db.order.OrderItemEntity;
import com.ordermng.db.order.OrderItemRepository;

@Component
public class OrderItemComponent implements OrderItemBusiness {
    private OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrderItemComponent(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
        List<StockAmountDTO> result = new ArrayList<>();
        
        orderItemsList.forEach(oi -> result.add(modelMapper.map(oi, StockAmountDTO.class)));

        return result;
    }

    @Override
    public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
        List<OrderItemEntity> entities = orderItemRepository.findUnsatisfiedOrderItemsByItemCode(item.getCode());

        List<OrderItemDTO> orderItems = new ArrayList<>();
        
        entities.forEach(e -> orderItems.add(modelMapper.map(e, OrderItemDTO.class)));

        return orderItems;
    }
}
