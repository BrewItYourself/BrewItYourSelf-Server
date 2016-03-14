package com.brewityourself.server.dao;

import com.brewityourself.server.dto.BrewRecipe;

import java.util.List;

/**
 * Created by sjung on 12/03/16.
 */
public interface BrewRecipeDAO {

    /**
     * Gets a list of recipes from the Database
     * @return List of BrewRecipes
     */
    List<BrewRecipe> getRecipes();

    /**
     * Inserts a new recipe to the database
     * @param brewRecipe
     * @return true/false based on success
     */
    boolean inputRecipe(BrewRecipe brewRecipe);
}
