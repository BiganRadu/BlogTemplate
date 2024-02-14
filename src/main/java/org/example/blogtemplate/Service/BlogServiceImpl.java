package org.example.blogtemplate.Service;

import org.example.blogtemplate.DAO.BlogDao;
import org.example.blogtemplate.Entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    private BlogDao blogDao;

    @Autowired
    public BlogServiceImpl(BlogDao blogDao){
        this.blogDao = blogDao;
    }
    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.findAll();
    }

    @Override
    public int addBlog(Blog blog) {
        Blog newBlog = blogDao.save(blog);
        return newBlog.getId();
    }

    @Override
    public void deleteBlog(int id) {
        blogDao.deleteById(id);
    }

    @Override
    public Optional<Blog> getBlogById(int id) {
        return blogDao.findById(id);
    }
}
