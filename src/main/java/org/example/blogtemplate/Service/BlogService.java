package org.example.blogtemplate.Service;

import org.example.blogtemplate.Entity.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    public List<Blog> getAllBlogs();
    public int addBlog(Blog blog);

    public void deleteBlog(int id);

    public Optional<Blog> getBlogById(int id);
}
