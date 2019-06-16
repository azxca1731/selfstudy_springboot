package com.azxca1731.springbookweb;

import com.azxca1731.springbookweb.domain.Board;
import com.azxca1731.springbookweb.domain.User;
import com.azxca1731.springbookweb.domain.enums.BoardType;
import com.azxca1731.springbookweb.repository.BoardRepository;
import com.azxca1731.springbookweb.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(
            User.builder()
                .name("havi")
                .password("test")
                .email(email)
                .createdDate(LocalDateTime.now())
                .build()
        );
        boardRepository.save(
            Board.builder()
                .title(boardTestTitle)
                .subTitle("서브 타이틀")
                .content("Contents")
                .boardType(BoardType.free)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user)
                .build()
        );
    }

    @Test
    public void 제대로_생성됐는지_테스트() {
        User user = userRepository.findByEmail(email);

        assertThat(user.getName(),is("havi"));
        assertThat(user.getPassword(),is("test"));
        assertThat(user.getEmail(),is(email));

        Board board = boardRepository.findByUser(user);

        assertThat(board.getTitle(),is(boardTestTitle));
        assertThat(board.getSubTitle(),is("서브 타이틀"));
        assertThat(board.getContent(),is("Contents"));
        assertThat(board.getBoardType(),is(BoardType.free));
    }
}
