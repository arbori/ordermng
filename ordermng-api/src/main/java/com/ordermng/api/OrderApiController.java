package com.ordermng.api;

import com.ordermng.api.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@RestController
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Order> addOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new order in the store", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"item\" : { },\n  \"quantity\" : 42,\n  \"moviment\" : { },\n  \"active\" : true,\n  \"id\" : 29843,\n  \"creationDate\" : \"2021-10-18T08:32:28Z\",\n  \"user\" : { }\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteOrder(@Parameter(in = ParameterIn.HEADER, description = "Order id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Order>> listOrders() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Order>>(objectMapper.readValue("[ {\n  \"item\" : { },\n  \"quantity\" : 42,\n  \"moviment\" : { },\n  \"active\" : true,\n  \"id\" : 29843,\n  \"creationDate\" : \"2021-10-18T08:32:28Z\",\n  \"user\" : { }\n}, {\n  \"item\" : { },\n  \"quantity\" : 42,\n  \"moviment\" : { },\n  \"active\" : true,\n  \"id\" : 29843,\n  \"creationDate\" : \"2021-10-18T08:32:28Z\",\n  \"user\" : { }\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Order>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Order>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> updateOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent order in the store", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Order>(objectMapper.readValue("{\n  \"item\" : { },\n  \"quantity\" : 42,\n  \"moviment\" : { },\n  \"active\" : true,\n  \"id\" : 29843,\n  \"creationDate\" : \"2021-10-18T08:32:28Z\",\n  \"user\" : { }\n}", Order.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

}
