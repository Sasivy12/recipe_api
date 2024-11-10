package com.example.recipe_api.model;

import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "recipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeId;

    @NonNull
    private String title;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    private String ingredients;

    @NonNull
    private String instructions;

    @NonNull
    private Integer prepTime;

    @NonNull
    private Integer cookTime;

    @NonNull
    private Integer totalTime;

    @NonNull
    private Integer servings;

    @NonNull
    private String category;

    @NonNull
    private String tags;

    @NonNull
    private LocalDate createdAt;

    @NonNull
    private LocalDate updatedAt;

    @NonNull
    private Double ratings;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("recipe-reviews")
    private Set<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-recipes")
    private User user;
}
