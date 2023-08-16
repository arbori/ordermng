package com.ordermng.api.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordermng.core.dto.UserDTO;
import com.ordermng.core.user.UserBusiness;
import com.ordermng.db.user.UserEntity;
import com.ordermng.db.user.UserRepository;

@Component
public class UserComponent implements UserBusiness {
    private final UserRepository repository;

    @Autowired
    public UserComponent(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String addNewUser(UserDTO user) {
        Optional<UserDTO> userRetrieved = findUserByEmail(user.getEmail());
    
        if(userRetrieved.isPresent()) {
            return userRetrieved.get().getEmail();
        }

        UserEntity userEntity = repository.save(new UserEntity(null, user.getEmail(), user.getName(), true));

        return userEntity.getEmail();
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = repository.findActiveByEmail(email);

        if(userEntityOptional.isPresent()) {
            UserEntity entity = userEntityOptional.get();

            return Optional.of(new UserDTO(entity.getEmail(), entity.getName(), entity.getActive()));
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> inactiveUser(String email) {
        Optional<UserEntity> userEntityOptional = repository.findActiveByEmail(email);

        if(userEntityOptional.isPresent()) {
            userEntityOptional.get().setActive(false);

            repository.save(userEntityOptional.get());

            return Optional.of(userEntityOptional.get().getEmail());
        }

        return Optional.empty();
    }

    /**
     * Get the list of active items.
     * @return A list of active items.
     */
    public Iterable<UserEntity> findAllActive() {
        return repository.findAllActive();
    }

    public Optional<UserEntity> findActiveByEmail(String email) {
        return repository.findActiveByEmail(email);
    }

    public UserEntity save(UserEntity itemEntity) {
        return repository.save(itemEntity);
    }
}
