package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "plant")
public class Plant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 40)
  private String name;

  @Column(length = 256)
  private String description;

  @OneToMany(mappedBy = "plant", targetEntity = Note.class)
  private Set<Note> note;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(name = "plant_garden",
          joinColumns = {
          @JoinColumn(name = "plant_id", referencedColumnName = "id", nullable = false, updatable = false)},
          inverseJoinColumns = {
          @JoinColumn(name = "garden_id", referencedColumnName = "id", nullable = false, updatable = false)})
  private Set<Garden> garden = new HashSet<>();
}
