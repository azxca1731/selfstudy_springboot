package com.azxca1731.springbookweb;

import com.azxca1731.springbookweb.domain.Board;
import com.azxca1731.springbookweb.domain.User;
import com.azxca1731.springbookweb.domain.enums.BoardType;
import com.azxca1731.springbookweb.repository.BoardRepository;
import com.azxca1731.springbookweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBookWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository,
        BoardRepository boardRepository) throws Exception {
        return args -> {
            User user = userRepository.save(
                    User.builder()
                            .name("havi")
                            .password("test")
                            .email("test@gmail.com")
                            .createdDate(LocalDateTime.now())
                            .build()
            );

            IntStream.rangeClosed(1, 200).forEach(index ->
                boardRepository.save(
                        Board.builder()
                            .title("게시글"+index)
                            .subTitle("순서"+index)
                            .content("콘텐츠")
                            .boardType(BoardType.free)
                            .createdDate(LocalDateTime.now())
                            .updatedDate(LocalDateTime.now())
                            .user(user)
                            .build()
                )
            );
        };
    }

}
