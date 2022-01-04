package com.pk.projekt;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @OneToMany(mappedBy = "garden", targetEntity = Task.class)
  private Set<Task> task;

  @OneToMany(mappedBy = "garden", targetEntity = Note.class)
  private Set<Note> note;

  @ManyToMany(mappedBy = "garden", fetch = FetchType.LAZY, targetEntity = Plant.class)
  private Set<Plant> plant = new HashSet<>();

}
