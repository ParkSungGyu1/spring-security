package com.spring.loginprac.service;

import com.spring.loginprac.dto.SignupRequestDto;
import com.spring.loginprac.model.UserRoleEnum;
import com.spring.loginprac.model.Users;
import com.spring.loginprac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String email = signupRequestDto.getEmail();
        UserRoleEnum role = UserRoleEnum.USER;

        Optional<Users> found = userRepository.findByUsername(username);

        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 ID가 존재합니다.");
        }


        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀립니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        Users users = new Users(username, password, email, role);
        userRepository.save(users);

    }
}
