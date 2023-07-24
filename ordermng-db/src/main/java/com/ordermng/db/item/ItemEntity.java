package com.ordermng.db.item;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ordermng.core.item.Item;

import javax.persistence.Column;

@Entity
@Table(name =  "ordermng_item")
public class ItemEntity extends Item {
	public ItemEntity() {
		super();
	}
	public ItemEntity(String code, String name, Boolean active) {
		super(code, name, active);
	}
	public ItemEntity(ItemEntity itemEntity) {
		super(itemEntity);
	}
	
	@Id
	@Column(name = "item_code", length = 12)
	@Override
	public String getCode() {
		return super.getCode();
	}

	@Column(name = "item_name", length = 40)
	@Override
	public String getName() {
		return super.getName();
	}

	@Column(name = "item_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}
}
