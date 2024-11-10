package com.example.recipe_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
