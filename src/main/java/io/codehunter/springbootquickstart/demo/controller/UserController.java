package io.codehunter.springbootquickstart.demo.controller;

import io.codehunter.springbootquickstart.demo.entity.User;
import io.codehunter.springbootquickstart.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestBody User user){
//        User u = new User();
//        u.setName(name);
//        u.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/find/{id}")
    public @ResponseBody User findUserById(@PathVariable("id") Long id){
        return userRepository.findOne(id);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        user.setId(id);
        userRepository.save(user);
        return "updated";
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deleteUserById(@PathVariable("id") Long id){
        userRepository.delete(id);
        return "deleted";
    }

}
