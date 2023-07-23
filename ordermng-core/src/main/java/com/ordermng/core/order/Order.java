package com.ordermng.core.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ordermng.core.user.User;

public class Order {
    private LocalDateTime creationDate;
    private User user;
    private List<OrderItem> orderItemsList;
    private Boolean active;

    public Order(LocalDateTime creationDate, User user, List<OrderItem> orderItemsList) {
        this.creationDate = creationDate;
        this.user = user;
        this.orderItemsList = new ArrayList<>();
        this.active = true;

        this.orderItemsList.addAll(orderItemsList);

        // TODO Every attributes need to be not null and orderItemsList must have almost one item inside.
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
    public List<OrderItem> getOrderItemsList() {
        return orderItemsList;
    }
    public void addItemOrder(OrderItem itemOrder) {
        this.orderItemsList.add(itemOrder);
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
            .append("{\"creationDate\"=\"").append(creationDate).append("\"")
            .append(", \"user\"=").append(user)
            .append(", \"orderItemsList\"=[");

        int size = this.orderItemsList.size();

        for(int i = 0; i < size; i++) {
            OrderItem item = this.orderItemsList.get(i);

            sb.append(item.toString()).append(i < size - 1 ? ", " : "");
        }
        
        sb.append("], \"active\"=\"").append(active).append("\"}");
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int[] result = new int[] {1};

        result[0] = prime * result[0] + ((creationDate == null) ? 0 : creationDate.hashCode());
        result[0] = prime * result[0] + ((user == null) ? 0 : user.hashCode());
        this.orderItemsList.stream().forEach(item -> result[0] = prime * result[0] + ((item == null) ? 0 : item.hashCode()));
        result[0] = prime * result[0] + ((active == null) ? 0 : active.hashCode());
        
        return result[0];
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

        if(this.orderItemsList.size() != other.getOrderItemsList().size()) {
            return false;
        } else {
            for(OrderItem item: this.orderItemsList) {
                if (!other.getOrderItemsList().contains(item)) {
                    return false;
                }
            }
        }


        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        return true;
    }   
}
