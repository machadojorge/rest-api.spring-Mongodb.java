package com.workshop.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workshop.workshopmongo.domain.User;

@Repository  // This is important because we say to Spring that class is a interface of type repository to access the data in database
//they need extends the MongoRepository and we need to identify what object we will catch and the type os the data format
// The mongoRepository has many operations CRUD to to the database
public interface UserRepository extends MongoRepository <User, String> {
    
}
