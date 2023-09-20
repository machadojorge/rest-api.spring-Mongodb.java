package com.workshop.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.domain.User;
import com.workshop.workshopmongo.dto.AuthorDTO;
import com.workshop.workshopmongo.dto.CommentDTO;
import com.workshop.workshopmongo.repository.PostRepository;
import com.workshop.workshopmongo.repository.UserRepository;

// this class is for tests. Whenever initialize the application, this class put data in the database for we can testing our applicatin
@Configuration // this annotation indicates to Spring that is just configuration class
public class Instantiation implements CommandLineRunner {


    // now we inject in this class our "UserRepository" for this class can connect to the database
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // First, we delete all records in the database
        userRepository.deleteAll();
        postRepository.deleteAll();

        // Affter that, we put the new register in the database
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        // First we save the user for get the ids in the database
        // after that we add to the database the posts, so, this way, the users have Id, and the post will be have associate User Id
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viage", "Vou viajar para São Paulo", new AuthorDTO(maria));
         Post post2 = new Post(null, sdf.parse("21/12/2012"), "Ainda vou", "Vou viajar para Portugal", new AuthorDTO(maria));
       
        
         CommentDTO c1 = new CommentDTO("Boa viagem Mano", sdf.parse("21/03/2018"), new AuthorDTO(alex));
         CommentDTO c2 = new CommentDTO("Aproveitem", sdf.parse("21/05/2020"), new AuthorDTO(bob));
         CommentDTO c3 = new CommentDTO("Tenham um optimo dia", sdf.parse("30/05/2020"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        
        postRepository.saveAll(Arrays.asList(post1, post2));
        // now we add the post to the list Post from the user class for we have associated data
        maria.getPost().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
