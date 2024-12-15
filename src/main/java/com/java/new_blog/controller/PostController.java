package com.java.new_blog.controller;

import com.java.new_blog.entity.Category;
import com.java.new_blog.entity.Post;
import com.java.new_blog.service.CategoryService;
import com.java.new_blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  private CategoryService categoryService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts() {
    return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    if (post != null) {
      return new ResponseEntity<>(post, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
    Long categoryId = post.getCategory().getId();
    Category category = categoryService.getCategoryById(categoryId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + categoryId));
    post.setCategory(category);
    return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody Post post) {
    Long categoryId = post.getCategory().getId();
    Category category = categoryService.getCategoryById(categoryId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + categoryId));
    post.setCategory(category);
    Post updatedPost = postService.updatePost(id, post);
    if (updatedPost != null) {
      return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
    Post post = postService.getPostById(id);
    if (post != null) {
      postService.deletePost(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
