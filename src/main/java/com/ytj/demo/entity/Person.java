package com.ytj.demo.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //neo4j api需要空构造函数
    private Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
    public Set<Person> teamates;

    public void workWith(Person person) {
        if (teamates == null) {
            teamates = new HashSet<>();
        }
        teamates.add(person);
    }

    @Override
    public String toString() {
        return this.name + "'s teammates => " + Optional.ofNullable(this.teamates).orElse(Collections.emptySet()).stream().map(Person::getName).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
