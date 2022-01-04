package com.pk.projekt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class GardenRepositoryTests {

  @Autowired
  private GardenRepository repo;

  @Autowired
  private UserRepository repoUser;

  @Autowired
  private PlantRepository repoPlant;

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

  @Test
  public void testAddPlant() {
    Garden garden = repo.findByGardenId(Long.valueOf(1));

    Plant plant1 = repoPlant.findByPlantId(Long.valueOf(1));
    Plant plant2 = repoPlant.findByPlantId(Long.valueOf(2));
    Plant plant3 = repoPlant.findByPlantId(Long.valueOf(3));

    garden.getPlant().addAll(Arrays.asList(plant1, plant2, plant3));

    repo.save(garden);
  }

  @Test
  public void testAddDescription() {
    Garden garden = repo.findByGardenId(Long.valueOf(1));

    garden.setDescription("Ładny ogród");

    repo.save(garden);
  }


}
