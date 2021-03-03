package com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.controller;

import com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.data.UserEntity;
import com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.service.UserService;
import com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.shared.UserDto;
import com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.ui.model.CreateUserRequestModel;
import com.appdeveloper.photoapp.api.users.PhotoAppApiUsers.ui.model.CreateUserResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping("/status/check")
    public String getStatus(){
        return "working on port" + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser( @RequestBody CreateUserRequestModel userDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto user = userService.createUser(userDto);

        CreateUserResponseModel responseBody = modelMapper.map(user, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
