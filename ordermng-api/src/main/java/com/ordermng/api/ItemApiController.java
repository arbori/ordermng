package com.ordermng.api;

import com.ordermng.api.model.Result;
import com.ordermng.api.model.Item;
import com.ordermng.api.transform.ItemTransform;
import com.ordermng.core.ItemBusiness;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@RestController
public class ItemApiController implements ItemApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final CrudRepository<com.ordermng.db.Item, Long> repository;

    @Autowired
    public ItemApiController(ObjectMapper objectMapper, HttpServletRequest request, CrudRepository<com.ordermng.db.Item, Long> repository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.repository = repository;
    }

    public ResponseEntity<Result> addItem(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new item in the store", required=true, schema=@Schema()) @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                com.ordermng.db.Item item = ItemTransform.api2model(body);
                item.setActive(true);

                if(ItemBusiness.validate(item)) {
                    body = ItemTransform.model2api(repository.save(item));
                    
                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The item was add", body), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "The Item is invalid", body), 
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

    public ResponseEntity<Result> deleteItem(@Parameter(in = ParameterIn.HEADER, description = "Item id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="name", required=false) String name) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                repository.deleteById(id);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), String.format("An Item with id %d was deleted", id), null), 
                    HttpStatus.OK);
            } catch(Exception e) {
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

    public ResponseEntity<Result> listItems() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                List<Item> list = new ArrayList<>();

                repository.findAll().forEach(o -> list.add(ItemTransform.model2api(o)));
                
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

    public ResponseEntity<Result> updateItem(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent item in the store", required=true, schema=@Schema()) @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<com.ordermng.db.Item> optionalUser = repository.findById(body.getId());
                com.ordermng.db.Item item = ItemTransform.api2model(body);

                if(optionalUser.isPresent() && ItemBusiness.validate(item)) {
                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", ItemTransform.model2api(repository.save(item))), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "Item does not exist or invalid", body), 
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
