package com.example.recipe_api.model;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "reviews")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Message is mandatory")
    @Size(max = 1000, message = "Comment should not be more than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @NotNull(message = "Rating is mandatory")
    @Min(value = 0, message = "Rating should not be less than 0")
    @Max(value = 5, message = "Rating should not be more than 5")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-reviews")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference("recipe-reviews")
    private Recipe recipe;

    public Review(Long id, String comment, Double rating, User user)
    {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
    }

    public Review(String comment, Double rating, User user)
    {
        this.comment = comment;
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

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
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
