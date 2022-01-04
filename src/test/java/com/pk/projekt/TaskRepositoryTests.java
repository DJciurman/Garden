package com.pk.projekt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TaskRepositoryTests {

  @Autowired
  private TaskRepository repo;

  @Autowired
  private GardenRepository repoGarden;

  @Autowired
  private UserRepository repoUser;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreatTask() {
    Task task = new Task();
    User user = repoUser.findByEmail("test@test.test");
    Garden garden = repoGarden.findByGardenId(Long.valueOf(1));

    task.setDescription("Podlej kaktusy");
    task.setUser(user);
    task.setGarden(garden);

    Task savedTask = repo.save(task);

    Task existTask = entityManager.find(Task.class, savedTask.getId());

    assertThat(existTask.getId()).isEqualTo(task.getId());
  }


}
