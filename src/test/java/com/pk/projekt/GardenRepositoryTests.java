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
public class GardenRepositoryTests {

  @Autowired
  private GardenRepository repo;

  @Autowired
  private UserRepository repoUser;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateGarden() {
    Garden garden = new Garden();
    User user = repoUser.findByEmail("test@test.test");

    garden.setUser(user);
    garden.setAddress("Kraków 4");
    garden.setName("Czwarty");

    Garden savedGarden = repo.save(garden);

    Garden existGarden = entityManager.find(Garden.class, savedGarden.getId());

    assertThat(existGarden.getId()).isEqualTo(garden.getId());
  }


}
