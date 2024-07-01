package com.example.recipe_api.Review;

import com.example.recipe_api.Recipe.Recipe;
import com.example.recipe_api.Recipe.RecipeRepository;
import com.example.recipe_api.User.User;
import com.example.recipe_api.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Review> getReviewsByRecipeId(Long recipeId)
    {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        return recipe.map(reviewRepository::findByRecipe).orElseGet(Collections::emptyList);
    }

    public Review createReview(Long userId, Long recipeId, Review review)
    {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        review.setRecipe(recipeOptional.get());

        Optional<User> userOptional = userRepository.findById(userId);
        review.setUser(userOptional.get());

        return reviewRepository.save(review);
    }

    public Review deleteReview(Long userId, Long recipeId, Long reviewId)
    {
        Optional<Recipe> userOptional = recipeRepository.findById(recipeId);

        if(userOptional.isPresent())
        {
            Optional<User> recipeOptional = userRepository.findById(userId);
            if(recipeOptional.isPresent())
            {
                Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
                if(reviewOptional.isPresent())
                {
                    Review review = reviewOptional.get();
                    reviewRepository.delete(review);
                }
                else throw new RuntimeException("Review provided is incorrect");

            }
            else throw new RuntimeException("Recipe provided is incorrect");

        }
        else throw new RuntimeException("User provided is incorrect");


        return null;
    }
}
