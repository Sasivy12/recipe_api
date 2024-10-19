package com.example.recipe_api.service;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.repository.RecipeRepository;
import com.example.recipe_api.model.Review;
import com.example.recipe_api.repository.ReviewRepository;
import com.example.recipe_api.model.User;
import com.example.recipe_api.repository.UserRepository;
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

    @Autowired
    private RecipeService recipeService;

    public List<Review> getReviewsByRecipeId(Long recipeId)
    {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        return recipe.map(reviewRepository::findByRecipe).orElseGet(Collections::emptyList);
    }

    public Review createReview(Long userId, Long recipeId, Review review)
    {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent())
        {
            review.setUser(userOptional.get());

            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

            if(recipeOptional.isPresent())
            {
                Recipe recipe = recipeOptional.get();
                review.setRecipe(recipe);

                reviewRepository.save(review);

                recipeService.updateAverageRating(recipe);
            }
            else
            {
                throw new RuntimeException("Provided recipe is not correct");
            }
        }
        else
        {
            throw new RuntimeException("Provided user is not correct") ;
        }

        return reviewRepository.save(review);
    }

    public void deleteReview(Long userId, Long recipeId, Long reviewId)
    {
        Optional<Recipe> userOptional = recipeRepository.findById(userId);

        if(userOptional.isPresent())
        {
            Optional<User> recipeOptional = userRepository.findById(recipeId);
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
    }

    public void updateReview(Long userId, Long recipeId, Long reviewId, Review updatedReview)
    {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent())
        {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

            if (recipeOptional.isPresent())
            {
                Optional<Review> reviewOptional = reviewRepository.findById(reviewId);

                if (reviewOptional.isPresent())
                {
                    Review review = reviewOptional.get();

                    if (review.getRecipe().getRecipeId().equals(recipeId) && review.getUser().getUser_id().equals(userId)) {
                        review.setComment(updatedReview.getComment());
                        review.setRating(updatedReview.getRating());

                        reviewRepository.save(review);

                        recipeService.updateAverageRating(review.getRecipe());
                    }
                    else
                    {
                        throw new RuntimeException("Review does not belong to the provided recipe or user");
                    }
                }
                else
                {
                    throw new RuntimeException("Review provided is incorrect");
                }
            }
            else
            {
                throw new RuntimeException("Recipe provided is incorrect");
            }
        }
        else
        {
            throw new RuntimeException("User provided is incorrect");
        }
    }
}
