package com.blogapi.blogapi.service;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.model.UserDto;
import com.blogapi.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDto createuser(UserDto userDto);

    UserDto updateuser(UserDto userDto, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUserById(Integer id);

    List<User> getUsersByPagination(int pageNo, int pageSize);
}
