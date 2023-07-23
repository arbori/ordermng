package com.ordermng.api;

import com.ordermng.api.model.Item;
import com.ordermng.api.model.Result;
import com.ordermng.api.transform.ItemTransform;
import com.ordermng.core.item.ItemUseCase;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.item.ItemRepository;
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
public class ItemApiController implements ItemApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final HttpServletRequest request;

    private final ItemRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public ItemApiController(HttpServletRequest request, ItemRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    public ResponseEntity<Result> addItem(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new item in the store", required=true, schema=@Schema()) @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                ItemEntity itemEntity = ItemTransform.requestToEntity(body);

                if(ItemUseCase.isValid(itemEntity)) {
                    repository.save(itemEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The item has been add", body.getCode()), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", body.getCode()), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), body.getCode()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Result> deleteItem(@Parameter(in = ParameterIn.HEADER, description = "Item code to delete" ,required=true,schema=@Schema()) @RequestHeader(value="code", required=true) String code) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<ItemEntity> optionalItemEntity = repository.findActiveByCode(code);

                if(optionalItemEntity.isPresent()) {
                    optionalItemEntity.get().setActive(false);

                    repository.save(optionalItemEntity.get());

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The item has been deleted", code), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", code), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), code), 
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

                repository.findAllActive().forEach(o -> list.add(ItemTransform.entityToResponse(o)));
                
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
                Optional<ItemEntity> optionalUser = repository.findActiveByCode(body.getCode());
                ItemEntity itemEntity = ItemTransform.requestToEntity(body);

                if(optionalUser.isPresent() && ItemUseCase.isValid(itemEntity)) {
                    ItemTransform.updateEntity(optionalUser.get(), itemEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", ItemTransform.entityToResponse(repository.save(optionalUser.get()))), 
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
