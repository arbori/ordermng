package com.ordermng.db.item;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ordermng_item")
public class ItemEntity {
	@Id
	@Column(name = "item_code", length = 12)
	private String code;

	@Column(name = "item_name", length = 40)
	private String name;

	@Column(name = "item_active")
	private Boolean active;
}
