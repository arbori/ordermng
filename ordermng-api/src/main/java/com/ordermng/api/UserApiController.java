package com.ordermng.api;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
@RestController
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> addUser(@Parameter(in = ParameterIn.DEFAULT, description = "Create a new user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                com.ordermng.db.User user = UserTransform.api2model(body);
                user.setActive(true);

                if(UserBusiness.validate(user)) {

                    body = UserTransform.model2api(userRepository.save(user));

                    return new ResponseEntity<User>(body, HttpStatus.OK);
                }

                return new ResponseEntity<User>(body, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.HEADER, description = "Item id to delete" ,required=true,schema=@Schema()) @RequestHeader(value="id", required=true) Long id,@Parameter(in = ParameterIn.HEADER, description = "" ,schema=@Schema()) @RequestHeader(value="name", required=false) String name) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            userRepository.deleteById(id);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> listUsers() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<User> usersApi= new ArrayList<>();

                userRepository.findAll().forEach(u -> {
                    User user = new User();

                    user.setId(u.getId());
                    user.setName(u.getName());
                    user.setEmail(u.getEmail());
                    user.setActive(u.getActive());

                    usersApi.add(user);
                });
                
                return new ResponseEntity<List<User>>(usersApi, HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "Update an existent user in the store", required=true, schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        
        if (accept != null && accept.contains("application/json")) {
            try {
                Optional<com.ordermng.db.User> optionalUser = userRepository.findById(body.getId());
                com.ordermng.db.User user = UserTransform.api2model(body);

                if(optionalUser.isPresent() && UserBusiness.validate(user)) {
                    return new ResponseEntity<User>(UserTransform.model2api(userRepository.save(user)), HttpStatus.OK);
                }

                return new ResponseEntity<User>(body, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
