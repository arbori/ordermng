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
import com.ordermng.core.order.OrderItem;
import com.ordermng.core.user.User;
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

	private List<OrderItemEntity> orderItemsEntity = new ArrayList<>();

	@OneToMany
    @JoinColumn(name = "orderitem_order_id")
    public List<OrderItemEntity> getOrderItemsEntity() {
        orderItemsEntity.clear();
        super.getOrderItems().forEach(m -> orderItemsEntity.add((OrderItemEntity) m));
        return orderItemsEntity;
    }
    public void setOrderItemsEntity(List<OrderItemEntity> orderItemEntities) {
        super.getOrderItems().clear();
		super.getOrderItems().addAll(orderItemEntities);
    }

    public OrderEntity() {
		super();
    }
    public OrderEntity(Long id, LocalDateTime creationDate, User user, List<OrderItem> orderItems) {
        super(id, creationDate, user, orderItems);
    }
    public OrderEntity(OrderEntity orderEntity) {
        super(orderEntity);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((orderItemsEntity == null) ? 0 : orderItemsEntity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		if (orderItemsEntity == null) {
			if (other.orderItemsEntity != null)
				return false;
		} else if (!orderItemsEntity.equals(other.orderItemsEntity))
			return false;
		return true;
	}
}
