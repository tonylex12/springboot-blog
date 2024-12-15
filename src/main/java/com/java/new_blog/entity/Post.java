package com.java.new_blog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  private LocalDateTime creationDate;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  public Post(String title, String content, Category category) {
    this.title = title;
    this.content = content;
    this.category = category;
    this.creationDate = LocalDateTime.now();
  }
}
