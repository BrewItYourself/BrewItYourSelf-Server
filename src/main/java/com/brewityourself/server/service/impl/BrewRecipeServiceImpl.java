package com.brewityourself.server.service.impl;

import com.brewityourself.server.dao.BrewRecipeDAO;
import com.brewityourself.server.dao.impl.BrewRecipeDAOImpl;
import com.brewityourself.server.dto.BrewRecipe;
import com.brewityourself.server.service.BrewRecipeService;

import java.util.List;

/**
 * Created by sjung on 09/03/16.
 */

public class BrewRecipeServiceImpl implements BrewRecipeService {

    private BrewRecipeDAO brewRecipeDAO;

    public BrewRecipeServiceImpl() {
        brewRecipeDAO = new BrewRecipeDAOImpl();
    }

    @Override
    public List<BrewRecipe> getRecipes() {
        return brewRecipeDAO.getRecipes();
    }

    @Override
    public boolean inputRecipe(BrewRecipe brewRecipe) {
        return brewRecipeDAO.inputRecipe(brewRecipe);
    }
}
