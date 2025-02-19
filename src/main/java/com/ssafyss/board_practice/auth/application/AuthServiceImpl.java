package com.ssafyss.board_practice.auth.application;

import com.ssafyss.board_practice.auth.constants.ErrorMessages;
import com.ssafyss.board_practice.auth.dto.SignInResponse;
import com.ssafyss.board_practice.auth.exception.DuplicatedEmailException;
import com.ssafyss.board_practice.auth.exception.SignInFailedException;
import com.ssafyss.board_practice.user.entity.User;
import com.ssafyss.board_practice.user.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void checkEmail(String email) {
        int count = userRepository.countByEmail(email);
        if (count >= 1) {
            throw new DuplicatedEmailException(ErrorMessages.EMAIL_DUPLICATED);
        }
    }

    @Override
    public void signUp(String email, String password) {
        checkEmail(email);
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.insert(user);
    }

    @Override
    public SignInResponse signIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new SignInFailedException(ErrorMessages.SIGN_IN_FAILED);
        }
        return new SignInResponse.Builder()
                .id(user.getId())
                .build();
    }
}
