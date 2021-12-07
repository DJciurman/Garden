package com.pk.projekt;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 45)
  private String email;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  @OneToMany(mappedBy = "user", targetEntity = Garden.class)
  private Set<Garden> garden;

  @OneToMany(mappedBy = "user", targetEntity = Task.class)
  private Set<Task> task;

  @OneToMany(mappedBy = "user", targetEntity = Note.class)
  private Set<Note> note;

}
