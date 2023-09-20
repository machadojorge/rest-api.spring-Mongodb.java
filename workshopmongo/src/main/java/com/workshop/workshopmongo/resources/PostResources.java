package com.workshop.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.resources.util.URL;
import com.workshop.workshopmongo.services.PostServices;

@RestController
@RequestMapping(value="/posts")
public class PostResources {

    @Autowired
    private PostServices service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id)
    {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

     @RequestMapping(value="/titlesearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue=" ") String text)
    // About that last line: The requestParameter, means the value is passed through Url with parameter
    // te "value=text" indicates the name of parameter in the url
    // the "defaultValue="" " means a default value if the text is invalid
    //String text is the variable where is saved the value of the url text;
    {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }




     @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
        @RequestParam(value="text", defaultValue=" ") String text,
        @RequestParam(value="minDate", defaultValue=" ") String minDate,
        @RequestParam(value="maxDate", defaultValue=" ") String maxDate
        ) 
    // About that last line: The requestParameter, means the value is passed through Url with parameter
    // te "value=text" indicates the name of parameter in the url
    // the "defaultValue="" " means a default value if the text is invalid
    //String text is the variable where is saved the value of the url text;
    {
        text = URL.decodeParam(text);
        Date min = URL.converDate(minDate, new Date(0L)); //this method returns a date min
        Date max = URL.converDate(maxDate,new Date());

        
               List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
    
}
