package com.workshop.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.workshopmongo.domain.User;

// this annotation indicate that class is a rest controller
@RestController
// this annotation is indicate what is the endpoint
@RequestMapping(value="/users")
public class UserResources {

    // this indicates waht the verb we used in this method
    // in this case, is a GET because we return a list of users
    // we can use @GeTMapping
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() // About this ResponseEntity<T>
    // This object goes encapsulate all necessary struct for us returns HTTP responses with possible
    // headers, errors. We Return a User list of type ResponseEntity
    {
        User maria = new User("1", "Maria Silva", "maria@gmail.com");
        User alex = new User("2", "Alex Brown", "alex@gmail.com");

        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria, alex));

        // For return this ResponseEntity we need instance this Method with the code HTTP
        // and put in front this, in the body message, the content that be sent, in this case, the list of users
        return ResponseEntity.ok().body(list);

    }
    
}
