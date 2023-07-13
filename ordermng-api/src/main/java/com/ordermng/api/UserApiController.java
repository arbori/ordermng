package com.ordermng.api;

import com.ordermng.api.model.Result;
import com.ordermng.api.model.User;
import com.ordermng.api.transform.UserTransform;
import com.ordermng.core.UserBusiness;
import com.ordermng.db.repository.UserRepository;
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
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserRepository repository;

    @Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request, UserRepository repository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.repository = repository;
    }

    public ResponseEntity<Result> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                com.ordermng.db.User user = UserTransform.api2model(body);
                user.setActive(true);

                if(UserBusiness.validate(user)) {
                    body = UserTransform.model2api(repository.save(user));

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user was add", body), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.BAD_REQUEST.value(), "The User is invalid", body), 
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

    public ResponseEntity<Result> deleteUser(@Parameter(in = ParameterIn.HEADER, description = "Item id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="name", required=false) String name) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                repository.deleteById(id);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.OK.value(), String.format("A User with id %d was deleted", id), id), 
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

    public ResponseEntity<Result> listUsers() {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                List<User> list = new ArrayList<>();

                repository.findAll().forEach(o -> list.add(UserTransform.model2api(o)));
                
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

    public ResponseEntity<Result> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<com.ordermng.db.User> optionalUser = repository.findById(body.getId());
                com.ordermng.db.User user = UserTransform.api2model(body);

                if(optionalUser.isPresent() && UserBusiness.validate(user)) {
                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", UserTransform.model2api(repository.save(user))), 
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
