package com.example.recipe_api.Review;

import com.example.recipe_api.Recipe.Recipe;
import com.example.recipe_api.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Review(Long id, String message, Double rating, User user)
    {
        this.id = id;
        this.message = message;
        this.rating = rating;
        this.user = user;
    }

    public Review(String message, Double rating, User user)
    {
        this.message = message;
        this.rating = rating;
        this.user = user;
    }

    public Review()
    {

    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }
}
