package com.memeals.meMealsApi.Meal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "meals")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Meal {

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<IngredientMeal> mealIngredients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "icon_url")
    private String iconUrl;

    public Meal() {
    }

    public Meal(String mealName, String iconUrl) {
        this.mealName = mealName;
        this.iconUrl = iconUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
