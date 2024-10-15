package com.example.recipe_api.service;

import com.example.recipe_api.model.Recipe;
import com.example.recipe_api.repository.RecipeRepository;
import com.example.recipe_api.model.User;
import com.example.recipe_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> getASpecificUser(Long user_id)
    {
        return userRepository.findById(user_id);
    }

    public Optional<User> deleteUser(Long user_id)
    {
        Optional<User> user = userRepository.findById(user_id);

        if(user.isPresent())
        {
            userRepository.delete(user.get());
        }
        else
        {
            throw new RuntimeException("User does not exist");
        }

        return user;
    }

    public User updateUser(Long user_id, User updatedUser)
    {
        Optional<User> existingUser = userRepository.findById(user_id);

        if(existingUser.isPresent())
        {
            User user = existingUser.get();

            user.setEmail(updatedUser.getEmail());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());

            userRepository.save(user);
        }
        else
        {
            throw new RuntimeException("User does not exist");
        }

        return updatedUser;
    }


    public Optional<Recipe> getRecipeByUser(Long recipe_id, Long userId)
    {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent())
        {
            return recipeRepository.findByRecipeIdAndUser(recipe_id, user.get());
        }
        else
        {
            return Optional.empty();
        }
    }
}