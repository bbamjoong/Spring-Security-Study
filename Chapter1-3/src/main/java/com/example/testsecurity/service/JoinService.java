package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 주입
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO) {

        //db에 이미 동일한 username을 가진 회원이 존재하면 그냥 return하겠음.
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        // bCryptPasswordEncoder Bean을 이용해 인코딩 후 password 저장
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
//        data.setRole("ROLE_USER");
        data.setRole("ROLE_ADMIN"); // admin으로 회원가입

        userRepository.save(data);
    }
}