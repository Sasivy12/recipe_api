package com.example.recipe_api.Recipe;

import com.example.recipe_api.User.User;
import com.example.recipe_api.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RecipeRepository recipeRepository;

    public Recipe addRecipeToUser(Long userId, Recipe recipe)
    {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();

            recipe.setUser(user);
            recipeRepository.save(recipe);

            user.getRecipes().add(recipe);
            return recipe;
        }
        else
        {
            throw new RuntimeException("User with this id not found");
        }
    }
}
