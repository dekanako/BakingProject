package com.example.android.bakingproject.data.POJOS;

import java.util.List;

public class Dish {

    private int id;
    private String name;//slaw
    private List<Ingredients>ingredients;
    private List<Steps>steps;
    private String image;
    private int serving;

    public int getServing() {//axxx
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

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
