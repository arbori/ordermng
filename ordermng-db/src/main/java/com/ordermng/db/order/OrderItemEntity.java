package com.ordermng.db.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.movement.StockMovementEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "ordermng_order_item")
public class OrderItemEntity {
   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderitem_id")
	private Long orderItemId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "orderitem_order_id")
    private OrderEntity orderEntity;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderitem_item_code")
    private ItemEntity itemEntity;

    @Column(name = "orderitem_quantity")
    private Double quantity;

    @OneToMany
    @JoinColumn(name = "movement_orderitem_id")
    private List<StockMovementEntity> movements = new ArrayList<>();

    public OrderItemEntity(OrderEntity orderEntity, ItemEntity itemEntity, Double quantity) {
        this.orderEntity = orderEntity;
        this.itemEntity = itemEntity;
        this.quantity = quantity;
    }
}
