package com.blogapi.blogapi.service;

import com.blogapi.blogapi.entity.User;
import com.blogapi.blogapi.exception.RecordNotFoundException;
import com.blogapi.blogapi.model.UserDto;
import com.blogapi.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createuser(UserDto userDto) {

        User u = dtoToEntity(userDto);
        User user = userRepository.save(u);
        return entityToDto(user);
    }

    @Override
    public UserDto updateuser(UserDto userDto, Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("No User found!!"));
        return entityToDto(userRepository.save(dtoToEntity(userDto)));
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("No User found!!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream().map(u -> entityToDto(u)).collect(Collectors.toList());
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsersByPagination(int pageNo, int pageSize) {

        //create pagerequest object
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
        //pass it to repos
        Page<User> pagingUser = userRepository.findAll(pageRequest);
        //pagingUser.hasContent(); -- to check pages are there or not


        Sort nameSort = Sort.by("name");
        Sort emailSort = Sort.by("email");

        Sort multiSort = emailSort.and(nameSort);

//        List<User> result = userRepository.findAll(multiSort);

        //Slice<User> result = userRepository.findByEmail("abcd@gmail.com", paging);
        return pagingUser.getContent();
    }

    User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        return user;
    }

    UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
