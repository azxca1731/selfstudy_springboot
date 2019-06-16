package com.azxca1731.springbookweb.repository;

import com.azxca1731.springbookweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
