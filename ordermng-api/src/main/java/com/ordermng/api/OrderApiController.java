package com.ordermng.api;

import com.ordermng.api.model.Order;
import com.ordermng.api.model.Result;
import com.ordermng.api.transform.OrderTransform;
import com.ordermng.core.order.OrderUseCase;
import com.ordermng.db.item.ItemRepository;
import com.ordermng.db.order.OrderEntity;
import com.ordermng.db.order.OrderItemEntity;
import com.ordermng.db.order.OrderItemRepository;
import com.ordermng.db.order.OrderRepository;
import com.ordermng.db.user.UserEntity;
import com.ordermng.db.user.UserRepository;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-23T12:06:14.117462+01:00[Europe/Lisbon]")
@RestController
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final HttpServletRequest request;

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository; 

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(HttpServletRequest request, OrderRepository orderRepository, OrderItemRepository orderItemRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.request = request;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<Result> addOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new order in the store", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                OrderEntity orderEntity = OrderTransform.requestToEntity(body);

                List<OrderItemEntity> orderItemsEntity = new ArrayList<>();
        
                body.getOrderItems().forEach(oapi -> {
                    OrderItemEntity orderItemEntity = OrderTransform.requestToEntity(oapi);
                    orderItemEntity.setOrderEntity(orderEntity);
        
                    orderItemsEntity.add(orderItemEntity);
                });
        
                if(OrderUseCase.isValid(orderEntity)) {
                    orderEntity.setUser(userRepository.findAndFillUser((UserEntity) orderEntity.getUser()));

                    itemRepository.checkItensInOrderItems(orderEntity.getOrderItemsEntity());

                    orderRepository.save(orderEntity);
                    orderItemRepository.saveAll(orderItemsEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The order has been add", body), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", body), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), body), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Result> deleteOrder(@Parameter(in = ParameterIn.HEADER, description = "Order id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<OrderEntity> optionalUserEntity = orderRepository.findById(id);

                if(optionalUserEntity.isPresent()) {
                    optionalUserEntity.get().setActive(false);

                    //repository.save(optionalUserEntity.get());

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user has been deleted", id), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", id), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), id), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Result> listOrders() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                List<Order> list = new ArrayList<>();

                orderRepository.findAllActive().forEach(o -> list.add(OrderTransform.entityToResponse(o)));
                
                return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), "", list), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Result> updateOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent order in the store", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<OrderEntity> optionalOrder = orderRepository.findActiveById(body.getId());
                OrderEntity orderEntity = OrderTransform.requestToEntity(body);

                if(optionalOrder.isPresent() && OrderUseCase.isValid(orderEntity)) {
                    OrderTransform.updateEntity(optionalOrder.get(), orderEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", OrderTransform.entityToResponse(orderRepository.save(optionalOrder.get()))), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "User does not exist or invalid", body), 
                    HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }
}
