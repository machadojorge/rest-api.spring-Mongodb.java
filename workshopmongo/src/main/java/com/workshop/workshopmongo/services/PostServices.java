package com.workshop.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.repository.PostRepository;
import com.workshop.workshopmongo.services.exception.ObjectNotFoundException;

// in this class, we emplements the CRUD functions applay to the database
@Service
public class PostServices {
    
    // we need the inicialized a object from UserRepository
    // for that, we use the annotation "@Autowired" for indicates to spring that operation is a dependnecym injection
    @Autowired
    private PostRepository repo;

   
    public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

//   public List<Post>findByTitle(String text)
//   {
//     // in here, he call the method declaired in the interface
//     // just this and the method works
//     // for last, creates a endpoint for this method
//     return repo.findByTitleContaining(text);

//   }


// this method using a method from querys from mongodb
  public List<Post>findByTitle(String text)
  {
    // in here, he call the method declaired in the interface
    // just this and the method works
    // for last, creates a endpoint for this method
    return repo.searchTitle(text);

  }

}
