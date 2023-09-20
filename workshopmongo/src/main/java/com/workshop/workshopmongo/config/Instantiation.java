package com.workshop.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.repository.UserRepository;

// this class is for tests. Whenever initialize the application, this class put data in the database for we can testing our applicatin
@Configuration // this annotation indicates to Spring that is just configuration class
public class Instantiation implements CommandLineRunner {


    // now we inject in this class our "UserRepository" for this class can connect to the database
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {

        // First, we delete all records in the database
        userRepository.deleteAll();

        // Affter that, we put the new register in the database
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
       
        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
