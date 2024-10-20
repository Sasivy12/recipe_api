package com.example.recipe_api.controller;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.service.RecipeService;
import com.example.recipe_api.model.Review;
import com.example.recipe_api.service.ReviewService;
import com.example.recipe_api.model.User;
import com.example.recipe_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/register")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user)
    {
        return userService.verify(user);
    }

    @GetMapping("/user")
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

    @PostMapping("/{userId}/recipe")
    public Recipe createRecipe(@PathVariable Long userId, @RequestBody Recipe recipe)
    {
        return recipeService.addRecipeToUser(userId, recipe);
    }

    @DeleteMapping("/{userId}/recipe/{recipeId}")
    public void deleteRecipe(@PathVariable Long userId, @PathVariable Long recipeId)
    {
        recipeService.deleteRecipe(userId, recipeId);
    }

    @PutMapping("/{userId}/recipe/{recipeId}")
    public Recipe updateRecipe(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody Recipe updatedRecipe)
    {
        return recipeService.updateRecipe(userId, recipeId, updatedRecipe);
    }

    @GetMapping("/{userId}/recipe/{recipeId}/review")
    public ResponseEntity<List<Review>> getReviewsForRecipe(@PathVariable Long userId, @PathVariable Long recipeId)
    {
        List<Review> reviews = reviewService.getReviewsByRecipeId(recipeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/{userId}/recipe/{recipeId}/review")
    public Review createReview(@PathVariable Long userId, @PathVariable Long recipeId, @RequestBody Review review)
    {
        return reviewService.createReview(userId, recipeId, review);
    }

    @DeleteMapping("/{userId}/recipe/{recipeId}/review/{reviewId}")
    public void deleteReview(@PathVariable Long userId, @PathVariable Long recipeId, @PathVariable Long reviewId)
    {
        reviewService.deleteReview(userId, recipeId, reviewId);
    }

    @PutMapping("/{userId}/recipe/{recipeId}/review/{reviewId}")
    public void updateReview(@PathVariable Long userId, @PathVariable Long recipeId, @PathVariable Long reviewId, @RequestBody Review upatedReview)
    {
        reviewService.updateReview(userId, recipeId, reviewId, upatedReview);
    }
}