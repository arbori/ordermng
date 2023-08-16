package com.ordermng.core.item;

import java.util.Optional;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.UserDTO;

public interface ItemBusiness {
    /**
     * Add a new item in the item repository with the restriction that item's code and name could not exist in repository.
     * 
     * @param item The new item asked to be added in repository.
     * @return Return the item code for this item. If the item is realy new, the same code sent is retrieved. But, if an item with the same name already exist, the previous code will be returned.
     */
    public String addNewItem(ItemDTO item);

    /**
     * Update the target item based on source one. This methos centralized 
     * what is suppose to update in an item and do not change attributes that identify the it. 
     * @param tar Target object
     * @param src Source object
     */
    public default void updateEntity(ItemDTO tar, ItemDTO src) {
        if(!tar.getCode().equals(src.getCode())) {
            return;
        }
        
        tar.setName(src.getName());
        tar.setActive(src.getActive());
    }

    /**
     * This method looks for an item with the provided name.
     * @param itemName
     * @return
     */
    public String findItemCodeByName(String itemName);

    /**
     * It is not supposed to remove an item from database. Instead, an item is turned inactive and cannot be used in any order anymore.
     * @param code The code of an item to be inactive.
     * @return Return the code of the inactived item.
     */
    public Optional<String> inactiveItem(String code);

    static final int CODE_SIZE = 12;
    static final int NAME_SIZE = 40;

    /**
     * 
     * @param item
     * @return
     */
    public default boolean isValid(ItemDTO item) {
        boolean valid = item != null &&
            item.getCode() != null && !item.getCode().isEmpty() && item.getCode().length() <= CODE_SIZE &&
            item.getName() != null && !item.getName().isEmpty() && item.getName().length() <= NAME_SIZE &&
            item.getActive() != null;

        if(valid) {
            try {
                Long.parseLong(item.getCode());
            } catch(NumberFormatException e) {
                valid = false;
            }
        }

        return valid;
    }
}
