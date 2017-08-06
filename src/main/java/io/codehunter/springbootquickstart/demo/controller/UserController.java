package io.codehunter.springbootquickstart.demo.controller;

import io.codehunter.springbootquickstart.demo.entity.User;
import io.codehunter.springbootquickstart.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Object addNewUser(@RequestBody User user){
        Map<String, String> message = new HashMap<>();
        user = userRepository.save(user);
        if(user == null){
            message.put("error","add error");
        }else{
            message.put("info","add ok");
        }
        return message;
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
    public @ResponseBody Object updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        Map<String, String> message = new HashMap<>();
        user.setId(id);
        userRepository.save(user);
        if(user == null){
            message.put("error","update error");
        }else{
            message.put("info","update ok");
        }
        return message;
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody Object deleteUserById(@PathVariable("id") Long id){
        Map<String, String> message = new HashMap<>();
        userRepository.delete(id);
        if (userRepository.findOne(id) != null) {
            message.put("error", "delete error");
        }else{
            message.put("info", "delete ok");
        }
        return message;
    }

}
