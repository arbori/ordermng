package com.ordermng.api;

import com.ordermng.api.component.UserComponent;
import com.ordermng.api.model.Result;
import com.ordermng.api.model.UserRequest;
import com.ordermng.core.dto.UserDTO;
import com.ordermng.db.user.UserEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.modelmapper.ModelMapper;
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

    private final UserComponent component;

    private final ModelMapper modelMapper;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(HttpServletRequest request, UserComponent component) {
        this.request = request;
        this.component = component;

        this.modelMapper = new ModelMapper();
    }

    public ResponseEntity<Result> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new user in the store", required=true, schema=@Schema()) @Valid @RequestBody UserRequest body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                UserDTO user = new UserDTO(body.getEmail(), body.getName(), true);

                if(component.isValid(user)) {
                    String email = component.addNewUser(user);

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "The user has been add", email), 
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

    public ResponseEntity<Result> deleteUser(@Parameter(in = ParameterIn.HEADER, description = "User email to delete", required=true,schema=@Schema()) @RequestHeader(value="email", required=true) String email) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<String> userEmailOptional = component.inactiveUser(email);

                if(userEmailOptional.isPresent()) {
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
                List<UserRequest> list = new ArrayList<>();

                component.findAllActive().forEach(o -> list.add(modelMapper.map(o, UserRequest.class)));
                
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

    public ResponseEntity<Result> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent user in the store", required=true, schema=@Schema()) @Valid @RequestBody UserRequest body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<UserEntity> optionalUser = component.findActiveByEmail(body.getEmail());
                UserDTO userDto = modelMapper.map(body, UserDTO.class);

                if(optionalUser.isPresent() && component.isValid(userDto)) {
                    optionalUser.get().setName(userDto.getName());

                    return new ResponseEntity<Result>(
                        new Result(HttpStatus.OK.value(), "", modelMapper.map(component.save(optionalUser.get()), UserRequest.class)), 
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
