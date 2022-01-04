package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

  @ManyToMany(mappedBy = "plant", fetch = FetchType.LAZY, targetEntity = Garden.class)
  private Set<Garden> garden = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Note> getNote() {
    return note;
  }

  public Set<Garden> getGarden() {
    return garden;
  }
}
