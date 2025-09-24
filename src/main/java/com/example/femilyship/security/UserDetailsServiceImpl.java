package com.example.femilyship.security;

import com.example.femilyship.entity.User;
import com.example.femilyship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 정보를 찾아옵니다.
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // 찾아온 User 엔티티를 우리가 만든 UserDetailsImpl 객체로 감싸서 반환합니다.
        // 이렇게 해야 Spring Security가 사용자 정보를 인식할 수 있습니다.
        return new UserDetailsImpl(user);
    }
}