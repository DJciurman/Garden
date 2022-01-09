package com.pk.projekt;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "garden")
public class Garden {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, length = 40)
  private String address;

  @Column(nullable = true, length = 64)
  private String description;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @OneToMany(mappedBy = "garden", targetEntity = Task.class, cascade = CascadeType.ALL)
  private Set<Task> task;

  @OneToMany(mappedBy = "garden", targetEntity = Note.class, cascade = CascadeType.ALL)
  private Set<Note> note;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(name = "plant_garden",
          joinColumns = {
                  @JoinColumn(name = "garden_id", referencedColumnName = "id", nullable = false, updatable = false)},
          inverseJoinColumns = {
                  @JoinColumn(name = "plant_id", referencedColumnName = "id", nullable = false, updatable = false)})
  private Set<Plant> plant = new HashSet<>();


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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Task> getTask() {
    return task;
  }

  public Set<Note> getNote() {
    return note;
  }

  public Set<Plant> getPlant() {
    return plant;
  }

}
