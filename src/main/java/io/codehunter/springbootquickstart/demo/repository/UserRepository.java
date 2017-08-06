package io.codehunter.springbootquickstart.demo.repository;

import io.codehunter.springbootquickstart.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
