package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 256)
  private String description;

  @Column(nullable = false)
  private Long ownerId;

  @ManyToOne
  @JoinColumn(name = "gardenId")
  private Garden garden;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "plantId")
  private Plant plant;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Garden getGarden() {
    return garden;
  }

  public void setGarden(Garden garden) {
    this.garden = garden;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }
}
