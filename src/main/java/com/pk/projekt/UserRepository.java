package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.id = ?1")
  User findByUserId(Long id);

  @Query("SELECT u FROM User u ORDER BY u.email ASC")
  List<User> findAllUsersASC();
}
