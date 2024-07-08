package com.example.recipe_api.Recipe;

import com.example.recipe_api.User.User;
import com.example.recipe_api.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void deleteRecipe(Long userId, Long recipeId)
    {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

            if (optionalRecipe.isPresent()) {
                Recipe recipe = optionalRecipe.get();
                User user = optionalUser.get();

                if (recipe.getUser().getUser_id().equals(user.getUser_id()))
                {
                    recipeRepository.delete(recipe);
                }
                else
                {
                    throw new IllegalStateException("User does not own this recipe");
                }
            }
            else
            {
                throw new EntityNotFoundException("Recipe not found");
            }
        }
        else
        {
            throw new EntityNotFoundException("User not found");
        }
    }

    public Recipe updateRecipe(Long userId, Long recipeId, Recipe updatedRecipe) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

            if (optionalRecipe.isPresent()) {
                Recipe recipe = optionalRecipe.get();

                if (recipe.getUser().getUser_id().equals(user.getUser_id())) {
                    recipe.setCategory(updatedRecipe.getCategory());
                    recipe.setCookTime(updatedRecipe.getCookTime());
                    recipe.setUpdatedAt(LocalDate.now());
                    recipe.setDescription(updatedRecipe.getDescription());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setPrepTime(updatedRecipe.getPrepTime());
                    recipe.setServings(updatedRecipe.getServings());
                    recipe.setTags(updatedRecipe.getTags());
                    recipe.setTitle(updatedRecipe.getTitle());
                    recipe.setTotalTime(updatedRecipe.getTotalTime());
                    recipe.setInstructions(updatedRecipe.getInstructions());
                    recipe.setCreatedAt(updatedRecipe.getCreatedAt());
                    recipe.setRatings(updatedRecipe.getRatings());

                    return recipeRepository.save(recipe);

                }
                else
                {
                    throw new IllegalStateException("User does not own this recipe");
                }
            }
            else
            {
                throw new EntityNotFoundException("Recipe not found");
            }
        }
        else
        {
            throw new EntityNotFoundException("User not found");
        }
    }

}