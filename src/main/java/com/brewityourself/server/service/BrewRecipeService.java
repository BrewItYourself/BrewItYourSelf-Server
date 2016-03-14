package com.brewityourself.server.service;

import com.brewityourself.server.dto.BrewRecipe;

import java.util.List;

/**
 * Created by sjung on 09/03/16.
 */
public interface BrewRecipeService  {
    /**
     * Returns the available recipes from the database
     *
     * @return List of All available recipes
     */
    List<BrewRecipe> getRecipes();

    /**
     * Creates a new recipe into the database
     * @param brewRecipe
     * @return true/false based on creation success
     */
    boolean inputRecipe(BrewRecipe brewRecipe);
}