package com.ordermng.api;

import com.ordermng.api.model.Result;
import com.ordermng.api.model.User;
import com.ordermng.api.transform.UserTransform;
import com.ordermng.core.user.UserUseCase;
import com.ordermng.db.user.UserEntity;
import com.ordermng.db.user.UserRepository;

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
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final HttpServletRequest request;

    private final UserRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(HttpServletRequest request, UserRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    public ResponseEntity<Result> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                UserEntity userEntity = UserTransform.requestToEntity(body);

                if(UserUseCase.isValid(userEntity)) {
                    repository.save(userEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user has been add", body.getEmail()), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", body.getEmail()), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), body.getEmail()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Result>(
            new Result(HttpStatus.NOT_IMPLEMENTED.value(), "NOT IMPLEMENTED", null), 
            HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Result> deleteUser(@Parameter(in = ParameterIn.HEADER, description = "User email to delete", required=true,schema=@Schema()) @RequestHeader(value="email", required=true) String email) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<UserEntity> optionalUserEntity = repository.findActiveByEmail(email);

                if(optionalUserEntity.isPresent()) {
                    optionalUserEntity.get().setActive(false);

                    repository.save(optionalUserEntity.get());

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user has been deleted", email), 
                        HttpStatus.OK);
                }

                return new ResponseEntity<Result>(
                        new Result(HttpStatus.BAD_REQUEST.value(), "Invalid request", email), 
                        HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);

                return new ResponseEntity<Result>(
                    new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), email), 
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

                repository.findAllActive().forEach(o -> list.add(UserTransform.entityToResponse(o)));
                
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
                Optional<UserEntity> optionalUser = repository.findActiveByEmail(body.getEmail());
                UserEntity userEntity = UserTransform.requestToEntity(body);

                if(optionalUser.isPresent() && UserUseCase.isValid(userEntity)) {
                    UserTransform.updateEntity(optionalUser.get(), userEntity);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", UserTransform.entityToResponse(repository.save(optionalUser.get()))), 
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
