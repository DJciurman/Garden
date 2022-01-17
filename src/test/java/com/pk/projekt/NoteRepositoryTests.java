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
public class NoteRepositoryTests {

  @Autowired
  private NoteRepository repo;

  @Autowired
  private GardenRepository repoGarden;

  @Autowired
  private PlantRepository repoPlant;

  @Autowired
  private UserRepository repoUser;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateNote() {
    Note note = new Note();
    User user = repoUser.findByEmail("test@test.test");
//    Garden garden = repoGarden.findByGardenId(Long.valueOf(1));
//    Plant plant = repoPlant.findByPlantId(Long.valueOf(1));

    note.setDescription("Test notatki");
    note.setOwnerId(user.getId());

    Note savedNote = repo.save(note);

    Note existNote = entityManager.find(Note.class, savedNote.getId());

    assertThat(existNote.getId()).isEqualTo(note.getId());
  }

}
