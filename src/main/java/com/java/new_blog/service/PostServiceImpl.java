package com.java.new_blog.service;

import com.java.new_blog.entity.Post;
import com.java.new_blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  @Override
  public Post getPostById(Long id) {
    return postRepository.findById(id).orElse(null);
  }

  @Override
  public Post createPost(Post post) {
    post.setCreationDate(LocalDateTime.now());
    return postRepository.save(post);
  }

  @Override
  public Post updatePost(Long id, Post post) {
    if (postRepository.existsById(id)) {
      Post existingPost = postRepository.findById(id).orElse(null);
      if (existingPost != null) {
        post.setCreationDate(existingPost.getCreationDate());
        post.setId(id);
        return postRepository.save(post);
      }
    }
    return null;
  }

  @Override
  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }
}
