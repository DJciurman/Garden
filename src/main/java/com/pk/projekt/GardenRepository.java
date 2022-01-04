package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GardenRepository extends JpaRepository<Garden, Long> {

  @Query("SELECT g FROM Garden g WHERE g.user = ?1")
  List<Garden> findByUserId(User user);
}
