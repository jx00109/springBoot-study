package com.ytj.demo.repository;

import com.ytj.demo.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findByName(String name);
}
