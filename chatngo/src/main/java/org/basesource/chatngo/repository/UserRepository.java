package org.basesource.chatngo.repository;

import java.util.Optional;

import org.basesource.chatngo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    boolean existsByName(String name);

}
