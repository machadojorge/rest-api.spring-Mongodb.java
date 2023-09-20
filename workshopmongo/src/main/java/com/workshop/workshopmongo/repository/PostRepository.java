package com.workshop.workshopmongo.repository;

import java.util.Date;
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


    // This method goes creates a query with a text, a date min and date max
    //the text need be in title, or body or comments of post, for that we use a "$or" operation for the mongoDB query
    // for limit between dates we use the operator "$and" from the mongodb querys
    @Query("{$and: [{date: {$gte:?1} }, {date: {$lte: ?2}}, {$or: [{'title': {$regex:?0, $options: 'i'}}, {'body': {$regex:?0, $options: 'i'}}, {'comments.text': {$regex:?0, $options: 'i'}} ] } ]}")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);

    // for parts about the last query:
    //in the first {} we using a operation for compare values from mongodb
    // {date: {$gte:?1}  its mean, we compare the fiel data if big that the value passed in parameter number 1 (Date minDate)

    // {date: {$lte: ?2}}  its means, we compare the field data if is more little that the value in the parameter passed in method number 2 (Date maxDate)

    // About the last operation
    // we need verify if the text is in: the title OR the body OR the comments, for that, we used a new query using the "or" operator
    // for search in this 3 options
    // this is the result  ->  {$or: [{'title': {$regex:?0, $options: 'i'}}, {'body': {$regex:?0, $options: 'i'}}, {'comments.text': {$regex:?0, $options: 'i'}} ] } ]}")
    // in the last search, we used the "comment.text" because is a list of comment and we want the text of this comments
}
