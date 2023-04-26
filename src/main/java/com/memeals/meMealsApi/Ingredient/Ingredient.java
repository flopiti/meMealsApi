package com.memeals.meMealsApi.Ingredient;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
}