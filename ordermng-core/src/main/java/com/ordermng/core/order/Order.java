package com.ordermng.core.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ordermng.core.user.User;

public class Order {
    private Long id;
    private LocalDateTime creationDate;
    private User user;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Boolean shipped = false;
    private Boolean active = true;

    public Order() {
    }
    public Order(Long id, LocalDateTime creationDate, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.creationDate = creationDate;
        this.user = user;
        this.orderItems.addAll(orderItems);
        this.shipped = false;
        this.active = true;

        // TODO Every attributes need to be not null and orderItemsList must have almost one item inside.
    }
    public Order(Order o) {
        this.id = o.id;
        this.creationDate = o.creationDate;
        this.user = o.user;
        this.orderItems.addAll(o.orderItems);
        this.shipped = o.shipped;
        this.active = o.active;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public Boolean getShipped() {
        return shipped;
    }
    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Order [id=").append(id).append(", creationDate=").append(creationDate).append(", user=").append(user).append(", orderItemsList=").append(orderItems).append(", shipped=").append(shipped).append(", active=").append(active).append("]").toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
        result = prime * result + ((shipped == null) ? 0 : shipped.hashCode());
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (orderItems == null) {
            if (other.orderItems != null)
                return false;
        } else if (!orderItems.equals(other.orderItems))
            return false;
        if (shipped == null) {
            if (other.shipped != null)
                return false;
        } else if (!shipped.equals(other.shipped))
            return false;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        return true;
    }
}
