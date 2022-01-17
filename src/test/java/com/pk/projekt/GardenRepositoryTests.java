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
import java.util.Collection;

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
    Garden garden = repo.findByGardenId(3L);

//    Plant plant1 = repoPlant.findByPlantId(4L);
    Plant plant2 = repoPlant.findByPlantId(Long.valueOf(2L));
    Plant plant3 = repoPlant.findByPlantId(Long.valueOf(3L));

    garden.getPlant().addAll(Arrays.asList(plant2, plant3));
//    garden.getPlant().add(plant1);

    repo.save(garden);
  }

  @Test
  public void testAddDescription() {
    Garden garden = repo.findByGardenId(1L);

    garden.setDescription("Ładny ogród");

    repo.save(garden);
  }

  @Test
  public void testDeleteGarden() {
    Garden garden = repo.findByGardenId(10L);

    repo.delete(garden);
  }


}
