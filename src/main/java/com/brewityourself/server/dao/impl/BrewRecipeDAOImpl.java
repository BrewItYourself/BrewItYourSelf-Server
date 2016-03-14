package com.brewityourself.server.dao.impl;

import com.brewityourself.server.dao.BrewRecipeDAO;
import com.brewityourself.server.dto.BrewRecipe;
import com.brewityourself.server.utils.BrewDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sjung on 12/03/16.
 */
public class BrewRecipeDAOImpl implements BrewRecipeDAO {

    private BrewDatabaseConnection brewDatabaseConnection;

    public BrewRecipeDAOImpl() {
        brewDatabaseConnection = new BrewDatabaseConnection();
    }

    @Override
    public List<BrewRecipe> getRecipes() {
        String sqlString = "SELECT DISTINCT recipe_name, boil_temp from brewrecipe";
        try {
            PreparedStatement preparedStatement = brewDatabaseConnection
                    .getJdbcConnection()
                    .prepareStatement(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean inputRecipe(BrewRecipe brewRecipe) {
        String sqlString = "INSERT INTO brewrecipe (boil_temp, recipe_name) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = brewDatabaseConnection
                    .getJdbcConnection()
                    .prepareStatement(sqlString);

            preparedStatement.setDouble(1, brewRecipe.getBoilTemperature());
            preparedStatement.setString(2, brewRecipe.getRecipeName());

            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
