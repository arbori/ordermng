package com.ordermng.db;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ordermng.core.domine.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ordermng_order")
public class OrderEntity {
    public OrderEntity(Order order) {
        this.id = order.getId();
        this.creationDate = order.getCreationDate();
        this.item = new ItemEntity(order.getItem());
        this.quantity = order.getQuantity();
        this.user = new UserEntity(order.getUser());
        this.active = order.getActive();
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

    @Column(name = "order_creation_date")
    private LocalDateTime creationDate;

    @OneToOne(cascade = CascadeType.ALL)
    private ItemEntity item;

    @Column(name = "order_quantity")
	private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @Column(name = "order_active")
    private Boolean active;   
}