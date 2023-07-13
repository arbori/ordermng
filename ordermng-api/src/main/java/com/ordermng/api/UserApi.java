/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.43).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ordermng.api;

import com.ordermng.api.model.Result;
import com.ordermng.api.model.User;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@Validated
public interface UserApi {

    @Operation(summary = "Add a new user to the store", description = "Add a new user to the store", tags={ "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input") })
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Result> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body);


    @Operation(summary = "Delete an user", description = "delete an user", tags={ "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "400", description = "Invalid pet value") })
    @RequestMapping(value = "/user",
        method = RequestMethod.DELETE)
    ResponseEntity<Result> deleteUser(@Parameter(in = ParameterIn.HEADER, description = "Item id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id, @Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="name", required=false) String name);


    @Operation(summary = "List users", description = "List all users available", tags={ "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),
        
        @ApiResponse(responseCode = "400", description = "Invalid status value") })
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Result> listUsers();


    @Operation(summary = "Update an existing user", description = "Update an existing user by Id", tags={ "user" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        
        @ApiResponse(responseCode = "404", description = "Item not found"),
        
        @ApiResponse(responseCode = "405", description = "Validation exception") })
    @RequestMapping(value = "/user",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Result> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body);
}

