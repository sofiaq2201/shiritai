package com.sofiaq.shiritai.security.service;

import com.sofiaq.shiritai.security.model.User;
import com.sofiaq.shiritai.security.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
 
    @Autowired
    UserRepository userRepo;
    
    public Optional<User> getByUsername(String username){
        return userRepo.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return userRepo.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email){
        return userRepo.existsByEmail(email);
    }
    
    public void saveUser(User user){
        userRepo.save(user);
    }
}
