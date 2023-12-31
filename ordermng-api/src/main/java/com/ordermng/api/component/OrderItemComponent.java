package com.ordermng.api.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.OrderManagerException;
import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.orderitem.OrderItemBusiness;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.item.ItemRepository;
import com.ordermng.db.movement.StockMovementRepository;
import com.ordermng.db.order.OrderItemEntity;
import com.ordermng.db.order.OrderItemRepository;

@Component
public class OrderItemComponent implements OrderItemBusiness {
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;
    private final StockMovementRepository stockMovementRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrderItemComponent(ItemRepository itemRepository, OrderItemRepository orderItemRepository, StockMovementRepository stockMovementRepository) {
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    @Override
    public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
        Optional<Double> amount;
        List<StockAmountDTO> result = new ArrayList<>();
        
        for(OrderItemDTO orderItemDTO: orderItemsList) {
            amount = stockMovementRepository.computeAmountByItemCode(orderItemDTO.getItem().getCode());

            result.add(new StockAmountDTO(orderItemDTO.getItem(), amount.isPresent() ? amount.get() : 0.0));
        }

        return result;
    }

    @Override
    public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
        List<OrderItemEntity> entities = orderItemRepository.findUnsatisfiedOrderItemsByItemCode(item.getCode());

        List<OrderItemDTO> orderItems = new ArrayList<>();
        
        entities.forEach(e -> orderItems.add(modelMapper.map(e, OrderItemDTO.class)));

        return orderItems;
    }

    /**
     * 
     * @param orderItemDTO
     * @return
     * @throws OrderManagerException
     */
    public ItemEntity findItemByOrderItemDTO(OrderItemDTO orderItemDTO) throws OrderManagerException {
        if(orderItemDTO.getItem() == null) {
            throw new OrderManagerException(String.format("Order item has a null Item: %s", orderItemDTO));
        }

        Optional<ItemEntity> itemEntityOptional = itemRepository.findActiveByCode(orderItemDTO.getItem().getCode());

        return !itemEntityOptional.isPresent() ? null : itemEntityOptional.get();
    }

    public OrderItemEntity save(OrderItemEntity oi) {
        return this.orderItemRepository.save(oi);
    }
}
