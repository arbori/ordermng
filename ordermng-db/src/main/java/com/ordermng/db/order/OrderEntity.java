package com.ordermng.db.order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ordermng.db.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordermng_order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "order_creation_date")
	private LocalDateTime creationDate;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_user_id")
	private UserEntity userEntity;
	
	@Column(name = "order_shipped")
	private Boolean shipped;

	@Column(name = "order_active")
	private Boolean active;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "orderitem_order_id")
	private List<OrderItemEntity> orderItemsEntity = new ArrayList<>();
}
