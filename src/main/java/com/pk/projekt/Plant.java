package com.pk.projekt;

import lombok.Data;

import javax.persistence.*;

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
}
