package com.example.femilyship.service;

import com.example.femilyship.entity.Topic;
import com.example.femilyship.entity.User;
import com.example.femilyship.repository.TopicRepository;
import com.example.femilyship.repository.UserRepository;
import com.example.femilyship.security.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // 1. 초기 사용자(작성자) 생성
        // 실제 운영 시에는 비밀번호를 암호화해야 합니다.
        User initialUser = new User("admin", "password", UserRoleEnum.ADMIN);
        userRepository.save(initialUser);

        // 2. 초기 토픽 데이터 생성
        Topic topic1 = new Topic(
                "JPA(Java Persistence API)",
                "JPA는 자바 ORM(Object-Relational Mapping) 기술에 대한 표준 명세입니다. 이를 통해 개발자는 SQL 쿼리를 직접 작성하지 않고도 데이터베이스 작업을 수행할 수 있습니다.",
                initialUser
        );

        Topic topic2 = new Topic(
                "React.js",
                "React는 사용자 인터페이스를 구축하기 위한 JavaScript 라이브러리입니다. 컴포넌트 기반 아키텍처를 사용하여 재사용 가능한 UI를 효율적으로 만들 수 있습니다.",
                initialUser
        );

        Topic topic3 = new Topic(
                "Docker 컨테이너",
                "Docker는 애플리케이션을 신속하게 구축, 테스트 및 배포할 수 있는 컨테이너화 플랫폼입니다. 이를 통해 개발 환경과 운영 환경의 차이로 인한 문제를 해결할 수 있습니다.",
                initialUser
        );

        // 3. 데이터베이스에 저장
        topicRepository.save(topic1);
        topicRepository.save(topic2);
        topicRepository.save(topic3);

        System.out.println("====== 초기 데이터가 성공적으로 저장되었습니다. ======");
    }
}

