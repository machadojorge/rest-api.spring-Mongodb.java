package com.workshop.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.workshop.workshopmongo.domain.Post;

@Repository  // This is important because we say to Spring that class is a interface of type repository to access the data in database
//they need extends the MongoRepository and we need to identify what object we will catch and the type os the data format
// The mongoRepository has many operations CRUD to to the database
public interface PostRepository extends MongoRepository <Post, String> {
    
    // In this case we used methods existes to the query in the spring boot
    // The Spring boot creates the query just with this declaration
    List<Post> findByTitleContainingIgnoreCase(String text); // this ignoreCase ignore Upper Case letters

    // this method we are do a query in the database using method from specific from MongoDB
    @Query("{'title': {$regex:?0, $options: 'i'}}")
    List<Post> searchTitle(String text);
}
