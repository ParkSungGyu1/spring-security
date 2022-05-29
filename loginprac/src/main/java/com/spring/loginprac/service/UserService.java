package com.spring.loginprac.service;

import com.spring.loginprac.dto.SignupRequestDto;
import com.spring.loginprac.model.UserRoleEnum;
import com.spring.loginprac.model.Users;
import com.spring.loginprac.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String row_password = signupRequestDto.getPassword();
        //패스워드 형식 확인
        //최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성하기
        if(username.length() < 3){
            return "닉네임의 길이를 3 이상으로 해주세요.";
        }

        if(row_password.length() < 4){
            return "패스워드의 길이를 4자 이상으로 해주세요";
        }

        if(row_password.equals(username)){
            return "닉네임과 패스워드는 다르게 해주세요.";
        }
        

        if(Pattern.matches( "^[a-zA-Z0-9]*$",username)){
            String password = passwordEncoder.encode(row_password);
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
            return "회원가입이 완료 되었습니다.";
        }else {
            return "아이디는 영문, 숫자를 혼합해 주세요";
        }

    }
}
