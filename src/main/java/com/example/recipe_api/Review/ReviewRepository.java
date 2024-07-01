package com.example.recipe_api.Review;

import com.example.recipe_api.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>
{
    List<Review> findByRecipe(Recipe recipe);
}
