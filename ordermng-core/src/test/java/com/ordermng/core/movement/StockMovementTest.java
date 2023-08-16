package com.ordermng.core.movement;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.StockAmountDTO;
import com.ordermng.core.dto.StockMovementDTO;
import com.ordermng.core.item.ItemBusiness;
import com.ordermng.core.orderitem.OrderItemBusiness;

public class StockMovementTest {
    private StockMovementBusiness stockMovementBusiness = new StockMovementBusiness() {
        @Override
        public String addNewStockMovement(StockMovementDTO stockMovement) {
            throw new UnsupportedOperationException("Unimplemented method 'addNewStockMovement'");
        }

        @Override
        public Optional<Long> inactiveStockMovement(StockMovementDTO stockMovement) {
            throw new UnsupportedOperationException("Unimplemented method 'inactiveStockMovement'");
        }

        @Override
        public boolean itCanBeInactivated(StockMovementDTO stockMovement) {
            throw new UnsupportedOperationException("Unimplemented method 'itCanBeInactivated'");
        }

    };

    private OrderItemBusiness orderItemBusiness = new OrderItemBusiness() {
        @Override
        public List<StockAmountDTO> retriveStockAmountFromOrderItems(List<OrderItemDTO> orderItemsList) {
            throw new UnsupportedOperationException("Unimplemented method 'retriveStockAmountFromOrderItems'");
        }

        @Override
        public List<OrderItemDTO> retrieveUnsatisfiedOrderItemsByItem(ItemDTO item) {
            throw new UnsupportedOperationException("Unimplemented method 'retrieveUnsatisfiedOrderItemsByItem'");
        }
    };

    private ItemBusiness itemBusiness = new ItemBusiness() {

        @Override
        public String addNewItem(ItemDTO item) {
            throw new UnsupportedOperationException("Unimplemented method 'addNewItem'");
        }

        @Override
        public String findItemCodeByName(String itemName) {
            throw new UnsupportedOperationException("Unimplemented method 'findItemCodeByName'");
        }

        @Override
        public Optional<String> inactiveItem(String code) {
            throw new UnsupportedOperationException("Unimplemented method 'inactiveItem'");
        }
    };

    @Test
    public void stockMovementInOrderWithDiffItem() {
        ItemDTO item = new ItemDTO("1234", "The Item", true);
        ItemDTO anotherItem = new ItemDTO("4321", "Another Item", true);

        OrderItemDTO orderItem = new OrderItemDTO(anotherItem, 100.0);

        StockMovementDTO stockMovement = new StockMovementDTO(item, orderItem, LocalDateTime.now(), 100.0, true);

        assertTrue("Itens in StockMovement and its OrderItem are no the same, but StockMovement is valid.", !stockMovementBusiness.isValid(stockMovement, orderItemBusiness, itemBusiness));
    }    
}
