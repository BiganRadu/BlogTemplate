package org.example.blogtemplate.Service;

import org.example.blogtemplate.DAO.UserRepository;
import org.example.blogtemplate.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public BlogUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Could not find the user");
        }
        return new BlogUserDetails(user);
    }
}
