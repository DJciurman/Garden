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
public class PlantRepositoryTests {

  @Autowired
  private PlantRepository repo;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateGarden() {
    Plant plant = new Plant();

    plant.setName("Ziemniak");
    plant.setDescription("Trzeba uważać na stonke");

    Plant savedPlant = repo.save(plant);

    Plant existPlant = entityManager.find(Plant.class, savedPlant.getId());

    assertThat(existPlant.getId()).isEqualTo(plant.getId());
  }
}
