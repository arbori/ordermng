package com.ordermng.api;

import com.ordermng.api.model.Order;
import com.ordermng.api.model.Result;
import com.ordermng.api.transform.OrderTransform;
import com.ordermng.api.transform.UserTransform;
import com.ordermng.core.OrderManagerException;
import com.ordermng.core.uc.OrderUseCase;
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

import com.ordermng.db.OrderEntity;
import com.ordermng.db.interactor.OrderInteractor;
import com.ordermng.db.repository.ItemRepository;
import com.ordermng.db.repository.OrderRepository;
import com.ordermng.db.repository.UserRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@RestController
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final HttpServletRequest request;

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    private final OrderInteractor orderInteractor;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(HttpServletRequest request, OrderInteractor orderInteractor, OrderRepository orderRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.request = request;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;

        this.orderInteractor = orderInteractor;
    }

    public ResponseEntity<Result> addOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new order in the store", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                body = OrderTransform.domineToApiModel(
                    orderInteractor.save(
                        OrderTransform.apiModelToDomine(
                            body)));
                    
                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), "The order was add", body), 
                    HttpStatus.OK);
            }
            catch (OrderManagerException ome) {
                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), ome.getMessage(), body), 
                    HttpStatus.BAD_REQUEST);
            }
            catch (Exception e) {
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
                orderInteractor.delete(id);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), "", id), 
                    HttpStatus.OK);
            }
            catch (OrderManagerException ome) {
                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "Order does not exist or invalid", id), 
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

    public ResponseEntity<Result> listOrders() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                List<Order> list = new ArrayList<>();

                orderInteractor.listOrders().forEach(o -> list.add(OrderTransform.domineToApiModel(o)));
                
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
                com.ordermng.core.domine.Order order = orderInteractor.updateOrder(OrderTransform.apiModelToDomine(body));

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), "", OrderTransform.domineToApiModel(order)), 
                    HttpStatus.OK);
            } catch (OrderManagerException ome) {
                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), ome.getMessage(), body), 
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
