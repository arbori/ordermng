package com.ordermng.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ordermng.core.domine.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ordermng_item")
public class ItemEntity {
	public ItemEntity(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.active = item.getActive();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(name = "item_name")
	private String name;

	@Column(name = "item_active")
	private Boolean active;

	public Item toDomine() {
		return new Item(id, name, active);
	}
}
