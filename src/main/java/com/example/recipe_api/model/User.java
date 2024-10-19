package com.example.recipe_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-reviews")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-recipes")
    private Set<Recipe> recipes;

    public User(Long user_id, String username, String password, String email)
    {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email)
    {
        this.username = username;
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
