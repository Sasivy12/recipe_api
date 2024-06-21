package com.example.recipe_api.Recipe;

import com.example.recipe_api.Review.Review;
import com.example.recipe_api.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String ingredients;

    private String instructions;

    private Integer prepTime;

    private Integer cookTime;

    private Integer totalTime;

    private Integer servings;

    private String category;

    private String tags;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private Double ratings;

    @OneToMany(mappedBy = "recipe")
    private Set<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Recipe(Long recipeId, String title, String description, String ingredients, String instructions, Integer prepTime, Integer cookTime, Integer totalTime, Integer servings, String category, String tags, LocalDate createdAt, LocalDate updatedAt, Double ratings)
    {
        this.recipeId = recipeId;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.servings = servings;
        this.category = category;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ratings = ratings;
    }

    public Recipe(String title, String description, String ingredients, String instructions, Integer prepTime, Integer cookTime, Integer totalTime, Integer servings, String category, String tags, LocalDate createdAt, LocalDate updatedAt, Double ratings)
    {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.servings = servings;
        this.category = category;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.ratings = ratings;
    }

    public Recipe()
    {

    }

    public Long getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(Long recipeId)
    {
        this.recipeId = recipeId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }

    public Integer getPrepTime()
    {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime)
    {
        this.prepTime = prepTime;
    }

    public Integer getCookTime()
    {
        return cookTime;
    }

    public void setCookTime(Integer cookTime)
    {
        this.cookTime = cookTime;
    }

    public Integer getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime)
    {
        this.totalTime = totalTime;
    }

    public Integer getServings()
    {
        return servings;
    }

    public void setServings(Integer servings)
    {
        this.servings = servings;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public LocalDate getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Double getRatings()
    {
        return ratings;
    }

    public void setRatings(Double ratings)
    {
        this.ratings = ratings;
    }

    public Set<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(Set<Review> reviews)
    {
        this.reviews = reviews;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}