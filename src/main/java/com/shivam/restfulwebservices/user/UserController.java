package com.shivam.restfulwebservices.user;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController {
    
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id){
        User user =  userDaoService.findOne(id);
        
        if(user == null)
            throw new UserNotFoundException("id-"+id);
        
        EntityModel entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getUsers());

        entityModel.add(linkBuilder.withRel("all-users"));


        return entityModel;
         
    
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        return userDaoService.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userDaoService.deleteById(id);
    }

}
