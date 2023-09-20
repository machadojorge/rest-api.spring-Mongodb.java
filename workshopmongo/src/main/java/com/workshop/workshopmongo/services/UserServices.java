package com.workshop.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.UserDTO;
import com.workshop.workshopmongo.repository.UserRepository;
import com.workshop.workshopmongo.services.exception.ObjectNotFoundException;

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
   
    public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

    // this methode receives a User object and insert this userObject inthe database
    public User insert(User obj)
    {
        return repo.insert(obj);
    }


    // For this method we get a User from the DTO Object
    public User fromDTO(UserDTO obj)
    {
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }

}
