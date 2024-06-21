package com.example.recipe_api.User;

import com.example.recipe_api.Recipe.Recipe;
import com.example.recipe_api.Review.Review;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    @NotBlank(message = "Username is mandatory")
    private String user_name;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Recipe> recipes;

    public User(Long user_id, String user_name, String password, String email)
    {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public User(String user_name, String password, String email)
    {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public User()
    {

    }

    public Long getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Long user_id)
    {
        this.user_id = user_id;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Set<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(Set<Review> reviews)
    {
        this.reviews = reviews;
    }

    public Set<Recipe> getRecipes()
    {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes)
    {
        this.recipes = recipes;
    }
}
