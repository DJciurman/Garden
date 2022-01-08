package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query("SELECT t FROM Task t WHERE t.user = ?1")
  List<Task> findByUserId(User user);

  @Query("SELECT t FROM Task t WHERE t.user = ?1 ORDER BY t.garden ASC")
  List<Task> findByUserIdGardenASC(User user);

  @Query("SELECT t FROM Task t WHERE t.garden = ?1")
  List<Task> findByGardenId(Garden garden);



}
