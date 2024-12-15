package com.java.new_blog.service;

import com.java.new_blog.entity.Post;
import java.util.List;

public interface PostService {
  List<Post> getAllPosts();

  Post getPostById(Long id);

  Post createPost(Post post);

  Post updatePost(Long id, Post post);

  void deletePost(Long id);
}
