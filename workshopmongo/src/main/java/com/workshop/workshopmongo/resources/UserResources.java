package com.workshop.workshopmongo.resources;

import java.net.URI;
/// IMPORTANTE  ///
//This class can be call the "Controller"
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.UserDTO;
import com.workshop.workshopmongo.services.UserServices;

// this annotation indicate that class is a rest controller
@RestController
// this annotation is indicate what is the endpoint
@RequestMapping(value="/users")
public class UserResources {

    // we need inject the Service in this class for call the methodos thar return for database allData
    // in this case, inject the "UserService" using @Autowired
    @Autowired
    private UserServices service;

    // this indicates waht the verb we used in this method
    // in this case, is a GET because we return a list of users
    // we can use @GeTMapping
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() // About this ResponseEntity<T>
    // This object goes encapsulate all necessary struct for us returns HTTP responses with possible
    // headers, errors. We Return a User list of type ResponseEntity
    {
        // User maria = new User("1", "Maria Silva", "maria@gmail.com");
        // User alex = new User("2", "Alex Brown", "alex@gmail.com");

        List<User> list = service.findAll();
       // list.addAll(Arrays.asList(maria, alex));


       // Now we need convert the User list to UserDTO list
       // we use a lambda expression: first convert the userlist to a Stream;
       //second use the map to convert any object User to a new Object UserDTO passing the userObject to the USERDTO constructor
       // after that, use collect() with parameter to convert to a list
       List<UserDTO> listDto = list.stream().map(x ->new UserDTO(x)).collect(Collectors.toList());
        // For return this ResponseEntity we need instance this Method with the code HTTP
        // and put in front this, in the body message, the content that be sent, in this case, the list of users
        return ResponseEntity.ok().body(listDto);

    }


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) //this means that id come from the URL
    {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

    // this is a Post Method that insert a new user in the database
    // Returns a RewsponseEntity<void>
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO obj) //Thsi RequestBody inform that we receive in the body an object of type UserDTO
        {
		User usr = service.fromDTO(obj);
        usr = service.insert(usr);

        // PUt a header in the response
        // gets the new address of inserted objected
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usr.getId()).toUri();
		// Return a response with headers and a body empty
        return ResponseEntity.created(uri).build();
	}

    // Delete
      @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) //this means that id come from the URL
    {
		service.delete(id);
        // When do any operation that not return nothing, we return the code 204 (noContent)
		return ResponseEntity.noContent().build();
	}

    //UPDATE
     @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO obj, @PathVariable String id) 
    //This RequestBody inform that we receive in the body an object of type UserDTO
    // and one ID for know what object makes changes
        {
		User usr = service.fromDTO(obj);
       usr.setId(id);
       usr = service.update(usr);
       
        return ResponseEntity.noContent().build();
	}


    /// Get the posts through ID
     @RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) //this means that id come from the URL
    {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPost());
	}


}
    

