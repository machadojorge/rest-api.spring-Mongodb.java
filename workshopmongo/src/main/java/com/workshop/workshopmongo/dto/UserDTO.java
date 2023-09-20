package com.workshop.workshopmongo.dto;

import java.io.Serializable;
//NOTE
// A DTO class is a class for select for the original classe or the many classe, just SOME attributes thess classes
// imagine, the classe User has 3 attributs, the UserDTO class can be just one atribute, because we need just one attribute
// this is important because this way can hidden the Class and important Resources of this class, some like usernames and passwords.

import com.workshop.workshopmongo.domain.User;
// Important //
//Now, in the class "UserResource" we do not return a list of "User"
// Now, We return a List of "UserDTO"
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;

    public UserDTO(){}

    public UserDTO ( User obj)
    {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
}
