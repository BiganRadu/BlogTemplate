package org.example.blogtemplate.DAO;

import org.example.blogtemplate.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDao extends JpaRepository<Blog, Integer> {
}
