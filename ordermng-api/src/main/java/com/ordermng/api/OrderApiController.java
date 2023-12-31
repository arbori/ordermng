package com.ordermng.api;

import com.ordermng.api.component.OrderComponent;
import com.ordermng.api.component.OrderItemComponent;
import com.ordermng.api.component.UserComponent;
import com.ordermng.api.model.OrderItemRequest;
import com.ordermng.api.model.OrderRequest;
import com.ordermng.api.model.Result;
import com.ordermng.core.dto.OrderDTO;
import com.ordermng.core.dto.OrderItemDTO;
import com.ordermng.core.dto.OrderType;
import com.ordermng.core.dto.UserDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.modelmapper.ModelMapper;
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

    private final OrderComponent orderComponent;
    private final OrderItemComponent orderItemComponent; 
    private final UserComponent userComponent;

    private final ModelMapper modelMapper;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(HttpServletRequest request, OrderComponent orderComponent, OrderItemComponent orderItemComponent, UserComponent userComponent) {
        this.request = request;
        this.orderComponent = orderComponent;
        this.orderItemComponent = orderItemComponent;
        this.userComponent = userComponent;

        this.modelMapper = new ModelMapper();
    }

    public ResponseEntity<Result> addOrder(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody OrderRequest orderRequest) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                if(orderRequest.getUser() == null || orderRequest.getUser().getEmail() == null || 
                        !userComponent.checkUserExists(orderRequest.getUser().getEmail()).isPresent()) {
                    return new ResponseEntity<Result>(
                            new Result(HttpStatus.BAD_REQUEST.value(), "User does not exist", orderRequest.getUser()), 
                            HttpStatus.BAD_REQUEST);
                }

                if(orderRequest.getOrderItems() == null || orderRequest.getOrderItems().isEmpty()) {
                    return new ResponseEntity<Result>(
                            new Result(HttpStatus.BAD_REQUEST.value(), "An order without items, is not possible", orderRequest.getOrderItems()), 
                            HttpStatus.BAD_REQUEST);
                }

                List<OrderItemDTO> orderItems = new ArrayList<>();    
                for(OrderItemRequest itemRequest: orderRequest.getOrderItems()) {
                    OrderItemDTO orderItemDTO = modelMapper.map(itemRequest, OrderItemDTO.class);
                    orderItems.add(orderItemDTO);
                } 
                
                UserDTO user = modelMapper.map(orderRequest.getUser(), UserDTO.class);
                OrderType type = "SALE".equals(orderRequest.getType()) ? OrderType.SALE : OrderType.PURCHASE;

                orderRequest = modelMapper.map(
                    orderComponent.save(orderComponent.newOrder(user, orderItems, type, orderItemComponent)),
                    OrderRequest.class);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), "The order has been add", ""), 
                    HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), ""), 
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
                Long deletedId = orderComponent.inactiveOrderById(id);

                if(deletedId != null) {
                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user has been deleted", deletedId), 
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
                return new ResponseEntity<Result>(new Result(HttpStatus.OK.value(), "", orderComponent.retrieveOrders()), HttpStatus.OK);
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

    public ResponseEntity<Result> updateOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent order in the store", required=true, schema=@Schema()) @Valid @RequestBody OrderRequest body) {
        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }
}
