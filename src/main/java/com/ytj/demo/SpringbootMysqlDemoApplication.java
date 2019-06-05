package com.ytj.demo;

import com.ytj.demo.entity.Person;
import com.ytj.demo.repository.PersonRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableNeo4jRepositories
@MapperScan("com.ytj.demo.mapper")
public class SpringbootMysqlDemoApplication {

    private final static Logger log = LoggerFactory.getLogger(SpringbootMysqlDemoApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMysqlDemoApplication.class, args);

    }

    @Bean
    CommandLineRunner demo(PersonRepository personRepository) {
        return args -> {
            personRepository.deleteAll();

            Person greg = new Person("Greg");
            Person roy = new Person("Roy");
            Person craig = new Person("craig");

            List<Person> team = Arrays.asList(greg, roy, craig);

            log.info("Before linking up with Neo4j...");

            team.stream().forEach(person -> log.info("\t" + person.toString()));

            personRepository.save(greg);
            personRepository.save(roy);
            personRepository.save(craig);

            roy = personRepository.findByName(roy.getName());
            roy.workWith(craig);
            personRepository.save(roy);

            log.info("Lookup each person by name");
            team.stream().forEach(person -> log.info("\t" + personRepository.findByName(person.getName())));
        };
    }
}
