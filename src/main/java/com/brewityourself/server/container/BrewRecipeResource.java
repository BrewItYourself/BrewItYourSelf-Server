package com.brewityourself.server.container;

import com.brewityourself.server.dto.BrewRecipe;
import com.brewityourself.server.service.BrewRecipeService;
import com.brewityourself.server.service.impl.BrewRecipeServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sjung on 09/03/16.
 */
@Path("/brewrecipe")
public class BrewRecipeResource {

    private BrewRecipeService brewRecipeService;
    public BrewRecipeResource() {
        brewRecipeService = new BrewRecipeServiceImpl();
    }

    @GET
    @Path("all_recipes")
    public Response getRecipes() {
        List<BrewRecipe> brewRecipesList = brewRecipeService.getRecipes();

        return Response
                .ok()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(brewRecipesList)
                .build();
    }

    @PUT
    @Path("new_recipe")
    public Response inputRecipe(BrewRecipe brewRecipe) {

        System.out.println("BrewRecipe:" + brewRecipe.getRecipeName());

        boolean success = brewRecipeService.inputRecipe(brewRecipe);

        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


}
