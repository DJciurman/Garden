package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "note")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 256)
  private String description;

  @ManyToOne
  @JoinColumn(name = "gardenId")
  private Garden garden;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne
  @JoinColumn(name = "plantId")
  private Plant plant;

}
