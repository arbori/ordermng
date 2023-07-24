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

import com.ordermng.core.order.Order;
import com.ordermng.db.user.UserEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

@Entity
@Table(name = "ordermng_order")
public class OrderEntity extends Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Column(name = "order_creation_date")
	@Override
	public LocalDateTime getCreationDate() {
		return super.getCreationDate();
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_user_id")
	public UserEntity getUserEntity() {
		return (UserEntity) super.getUser();
	}
	public void setUserEntity(UserEntity userEntity) {
		super.setUser(userEntity);
	}

	@Column(name = "order_active")
	@Override
	public Boolean getActive() {
		return super.getActive();
	}	
	
    public void add(OrderItemEntity entity) {
        super.getOrderItems().add(entity);
    }
    public OrderItemEntity get(int index) {
        return (OrderItemEntity) super.getOrderItems().get(index);
    }

	@OneToMany(mappedBy="orderEntity", fetch = FetchType.EAGER)
    public List<OrderItemEntity> getMovementsEntity() {
        List<OrderItemEntity> result = new ArrayList<>();
        super.getOrderItems().forEach(m -> result.add((OrderItemEntity) m));
        return result;
    }
    public void setMovementsEntity(List<OrderItemEntity> orderItemEntities) {
        super.getOrderItems().clear();
		super.getOrderItems().addAll(orderItemEntities);
    }
}
