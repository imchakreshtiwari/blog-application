package com.blogapi.blogapi.controller;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.model.UserDto;
import com.blogapi.blogapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto){
        UserDto userCreated =  userService.createuser(userDto);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getuser(@PathVariable("id") Integer id){
        UserDto userCreated =  userService.getUserById(id);
        //update the values
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAlluser(){
        List<UserDto> userCreated =  userService.getAllUsers();
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }

    ////////////Paging and Sorting examples/////////////////
    ////////////////////////////////////////////////////////

    @GetMapping
    public List<User> getUserWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){

        return userService.getUsersByPagination(pageNo,pageSize);

    }



}
