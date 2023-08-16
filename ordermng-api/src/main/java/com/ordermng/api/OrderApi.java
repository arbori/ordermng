/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.43).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ordermng.api;

import com.ordermng.api.model.Result;
import com.ordermng.api.model.OrderRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-23T12:06:14.117462+01:00[Europe/Lisbon]")
@Validated
public interface OrderApi {

    @Operation(summary = "Add a new Order to the store", description = "Add a new Order to the store", tags={ "order" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        
        @ApiResponse(responseCode = "405", description = "Invalid input") })
    @RequestMapping(value = "/order",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    public ResponseEntity<Result> addOrder(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody OrderRequest orderRequest);


    @Operation(summary = "Delete an order", description = "delete an order", tags={ "order" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        
        @ApiResponse(responseCode = "400", description = "Invalid pet value") })
    @RequestMapping(value = "/order",
        method = RequestMethod.DELETE)
    ResponseEntity<Result> deleteOrder(@Parameter(in = ParameterIn.HEADER, description = "Order id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id);

    @Operation(summary = "List uss", description = "List all uss available", tags={ "order" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderRequest.class)))),
        
        @ApiResponse(responseCode = "400", description = "Invalid status value") })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Result> listOrders();


    @Operation(summary = "Update an existing order", description = "Update an existing order by email", tags={ "order" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Item not found"),
        
        @ApiResponse(responseCode = "405", description = "Validation exception") })
    @RequestMapping(value = "/order",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Result> updateOrder(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent order in the store", required=true, schema=@Schema()) @Valid @RequestBody OrderRequest body);
}

