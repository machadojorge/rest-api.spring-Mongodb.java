package com.workshop.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.repository.UserRepository;

// in this class, we emplements the CRUD functions applay to the database
@Service
public class UserServices {
    
    // we need the inicialized a object from UserRepository
    // for that, we use the annotation "@Autowired" for indicates to spring that operation is a dependnecym injection
    @Autowired
    private UserRepository repo;

    
    public List<User> findAll()
    {
        // now, we just need use the "repo" variable and call the function that springBoot has for the database
        return repo.findAll();
    }
    
}
