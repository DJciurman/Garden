package com.pk.projekt;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<Garden> getGarden() {
    return garden;
  }

  public Set<Task> getTask() {
    return task;
  }

  public Set<Note> getNote() {
    return note;
  }
}
