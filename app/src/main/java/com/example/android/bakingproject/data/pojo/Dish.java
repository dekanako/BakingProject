package com.example.android.bakingproject.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dish {

    private int id;
    private String name;
    private List<Ingredients>ingredients;
    //@SerializedName("steps")
    private List<Steps> steps;
    private String image;
    @SerializedName("servings")
    private int serving;

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> Steps) {
        this.steps = Steps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
