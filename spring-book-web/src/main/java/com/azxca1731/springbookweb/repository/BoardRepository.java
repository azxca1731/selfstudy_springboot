package com.azxca1731.springbookweb.repository;

import com.azxca1731.springbookweb.domain.Board;
import com.azxca1731.springbookweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
