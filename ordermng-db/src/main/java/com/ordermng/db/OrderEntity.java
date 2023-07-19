package com.ordermng.db;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_id")
    private ItemEntity item;

    @Column(name = "order_quantity")
	private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_user_id")
    private UserEntity user;

    @Column(name = "order_active")
    private Boolean active;

    public Order toDomine() {
        return new Order(id, creationDate, item.toDomine(), quantity, user.toDomine(), active);
    }
}
