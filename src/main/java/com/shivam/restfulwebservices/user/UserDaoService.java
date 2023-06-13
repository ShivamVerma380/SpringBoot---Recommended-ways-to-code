package com.shivam.restfulwebservices.user;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static{
        users.add(new User(++usersCount, "Shivam", LocalDate.now().minusYears(20).minusMonths(3)));
        users.add(new User(++usersCount, "Karan", LocalDate.now().minusYears(24).minusMonths(1)));
        users.add(new User(++usersCount, "Omkar", LocalDate.now().minusYears(19).minusMonths(5)));
    }
    
    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public ResponseEntity<User> save(User user){
        user.setId(++usersCount);
        users.add(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
