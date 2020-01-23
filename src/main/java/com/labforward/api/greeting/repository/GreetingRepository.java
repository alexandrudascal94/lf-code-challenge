package com.labforward.api.greeting.repository;

import org.springframework.data.repository.CrudRepository;

import com.labforward.api.greeting.domain.Greeting;


public interface GreetingRepository extends CrudRepository<Greeting, String> {	

}