package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "gardenId", nullable = false)
  private Garden garden;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  @Column(nullable = false, length = 64)
  private String description;

}
