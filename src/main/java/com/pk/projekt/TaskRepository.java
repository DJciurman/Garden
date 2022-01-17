package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query("SELECT t FROM Task t WHERE t.user = ?1")
  List<Task> findByUserId(User user);

  @Query("SELECT t FROM Task t WHERE t.user = ?1 ORDER BY t.garden ASC")
  List<Task> findByUserIdGardenASC(User user);

  @Query("SELECT t FROM Task t WHERE t.garden = ?1")
  List<Task> findByGardenId(Garden garden);

  @Query("SELECT t FROM Task t WHERE t.id = ?1")
  Task findByTaskId(Long id);

  @Query("SELECT t FROM Task t WHERE t.garden in ?1 ORDER BY t.user.email ASC")
  List<Task> finByGardenListASC(List<Garden> gardens);



}
