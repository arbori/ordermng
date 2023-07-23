package com.ordermng.api;

import com.ordermng.api.model.Item;
import com.ordermng.api.model.Result;
import com.ordermng.api.model.StockMovement;
import com.ordermng.api.transform.ItemTransform;
import com.ordermng.api.transform.StockMovementTransform;
import com.ordermng.core.item.ItemUseCase;
import com.ordermng.core.movement.StockMovementUseCase;
import com.ordermng.db.item.ItemEntity;
import com.ordermng.db.item.ItemRepository;
import com.ordermng.db.movement.StockMovementEntity;
import com.ordermng.db.movement.StockMovementRepository;

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
public class StockMovementApiController implements StockMovementApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final HttpServletRequest request;

    private final StockMovementRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public StockMovementApiController(HttpServletRequest request, StockMovementRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    public ResponseEntity<Result> addStockMovement(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new stock movement in the store", required=true, schema=@Schema()) @Valid @RequestBody StockMovement body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                StockMovementEntity stockMovementEntity = StockMovementTransform.requestToEntity(body);

                if(StockMovementUseCase.isValid(stockMovementEntity)) {
                    stockMovementEntity = repository.save(stockMovementEntity);

                    body = StockMovementTransform.entityToResponse(stockMovementEntity);
                    
                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The stock movement has been add", body), 
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

    public ResponseEntity<Result> deleteStockMovement(@Parameter(in = ParameterIn.HEADER, description = "Stock movement id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<StockMovementEntity> optionalStockMovementEntity = repository.findActiveById(id);

                if(optionalStockMovementEntity.isPresent()) {
                    optionalStockMovementEntity.get().setActive(false);

                    repository.save(optionalStockMovementEntity.get());

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The item has been deleted", id), 
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

    public ResponseEntity<Result> listStockMovements() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                List<StockMovement> list = new ArrayList<>();

                repository.findAllActive().forEach(o -> list.add(StockMovementTransform.entityToResponse(o)));
                
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

    public ResponseEntity<Result> updateStockMovement(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent stock movement in the store", required=true, schema=@Schema()) @Valid @RequestBody StockMovement body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<StockMovementEntity> optionalStockMovement = repository.findActiveById(body.getId());
                StockMovementEntity stockMovementEntity = StockMovementTransform.requestToEntity(body);

                if(optionalStockMovement.isPresent() && StockMovementUseCase.isValid(stockMovementEntity)) {
                    StockMovementTransform.updateEntity(optionalStockMovement.get(), stockMovementEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", StockMovementTransform.entityToResponse(repository.save(optionalStockMovement.get()))), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "StockMovement does not exist or invalid", body), 
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
