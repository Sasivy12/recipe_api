package com.example.recipe_api.User;

import com.example.recipe_api.Recipe.Recipe;
import com.example.recipe_api.Recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/user/add")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{user_id}")
    public Optional<User> getASpecificUser(@PathVariable Long user_id)
    {
        return userService.getASpecificUser(user_id);
    }

    @DeleteMapping("/user/{user_id}")
    public Optional<User> deleteUser(@PathVariable Long user_id)
    {
        return userService.deleteUser(user_id);
    }

    @PutMapping("/user/{user_id}")
    public User updateUser(@PathVariable Long user_id, @RequestBody User updatedUser)
    {
        return userService.updateUser(user_id, updatedUser);
    }

    @GetMapping("/{userId}/recipe/{recipeId}")
    public Optional<Recipe> getRecipe(@PathVariable Long userId, @PathVariable Long recipeId)
    {
        return userService.getRecipeByUser(recipeId, userId);
    }

    @PostMapping("/{userId}/recipe/add")
    public Recipe createRecipe(@PathVariable Long userId, @RequestBody Recipe recipe)
    {
        return recipeService.addRecipeToUser(userId, recipe);
    }

    @DeleteMapping("/{userId}/recipe/{recipeId}")
    public Recipe deleteRecipe(@PathVariable Long userId, @PathVariable Long recipeId)
    {
        return recipeService.deleteRecipe(userId, recipeId);
    }

    @PutMapping("/{userId}/recipe/{recipeId}")
    public Recipe updateRecipe(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody Recipe updatedRecipe)
    {
        return recipeService.updateRecipe(userId, recipeId, updatedRecipe);
    }
}