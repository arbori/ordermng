package com.ordermng.api;

import com.ordermng.api.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ItemApiController implements ItemApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public ItemApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Item> addItem(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new item in the store", required=true, schema=@Schema()) @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Item>(objectMapper.readValue("{\n  \"name\" : \"Chair\",\n  \"active\" : true,\n  \"id\" : 29843\n}", Item.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Item>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteItem(@Parameter(in = ParameterIn.HEADER, description = "Item id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="name", required=false) String name) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Item>> listItems() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Item>>(objectMapper.readValue("[ {\n  \"name\" : \"Chair\",\n  \"active\" : true,\n  \"id\" : 29843\n}, {\n  \"name\" : \"Chair\",\n  \"active\" : true,\n  \"id\" : 29843\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Item>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Item>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Item> updateItem(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent item in the store", required=true, schema=@Schema()) @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Item>(objectMapper.readValue("{\n  \"name\" : \"Chair\",\n  \"active\" : true,\n  \"id\" : 29843\n}", Item.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Item>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Item>(HttpStatus.NOT_IMPLEMENTED);
    }

}
