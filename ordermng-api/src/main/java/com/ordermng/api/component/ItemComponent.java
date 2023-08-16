package com.ordermng.api.component;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.dto.ItemDTO;
import com.ordermng.core.item.ItemBusiness;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.item.ItemRepository;

@Component
public class ItemComponent implements ItemBusiness {
    private final ItemRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ItemComponent(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public String addNewItem(ItemDTO item) {
        String itemCode = findItemCodeByName(item.getName());

        if(itemCode == null || itemCode.isEmpty()) {
            ItemEntity itemEntity = repository.save(new ItemEntity(item.getCode(), item.getName(), item.getActive()));

            itemCode = itemEntity.getCode();
        }

        return itemCode;
    }

    public Optional<ItemDTO> updateEntity(ItemDTO src) {
        ItemDTO tar = null;
        Optional<ItemEntity> optionalItem = this.findActiveByCode(src.getCode());

        if(optionalItem.isPresent()) {
            tar = modelMapper.map(optionalItem.get(), ItemDTO.class);
            
            if(!this.isValid(tar)) {
                return Optional.empty();
            }
            
            this.updateEntity(tar, src);
            this.save(tar);

            return Optional.of(tar);
        }

        return Optional.empty();
    }

    @Override
    public String findItemCodeByName(String itemName) {
        return repository.findItemCodeByName(itemName);
    }
    
    @Override
    public Optional<String> inactiveItem(String code) {
        Optional<ItemEntity> optionalItemEntity = repository.findActiveByCode(code);

        if(optionalItemEntity.isPresent()) {
            optionalItemEntity.get().setActive(false);

            repository.save(optionalItemEntity.get());

            return Optional.of(optionalItemEntity.get().getCode());
        }

        return Optional.empty();
    }

    /**
     * Get the list of active items.
     * @return A list of active items.
     */
    public Iterable<ItemEntity> findAllActive() {
        return repository.findAllActive();
    }

    public Optional<ItemEntity> findActiveByCode(String code) {
        return repository.findActiveByCode(code);
    }

    public ItemEntity save(ItemDTO item) {
        return this.repository.save(modelMapper.map(item, ItemEntity.class));
    }

    public ItemEntity save(ItemEntity itemEntity) {
        return repository.save(itemEntity);
    }
}
