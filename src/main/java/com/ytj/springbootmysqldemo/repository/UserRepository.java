package com.ytj.springbootmysqldemo.repository;

import com.ytj.springbootmysqldemo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
