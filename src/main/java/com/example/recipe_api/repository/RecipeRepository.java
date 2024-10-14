package com.example.recipe_api.repository;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>
{
    Optional<Recipe> findByRecipeIdAndUser(Long recipe_id, User user);
}
