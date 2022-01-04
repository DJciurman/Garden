package com.pk.projekt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlantRepository extends JpaRepository<Plant, Long> {

  @Query("SELECT p FROM Plant p WHERE p.id = ?1")
  Plant findByPlantId(Long id);
}
