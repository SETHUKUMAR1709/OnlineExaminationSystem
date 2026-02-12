package com.sethukumar.OnlineExaminationSystem.services.implementation;

import com.sethukumar.OnlineExaminationSystem.models.Role;
import com.sethukumar.OnlineExaminationSystem.models.User;
import com.sethukumar.OnlineExaminationSystem.repository.UserRepository;
import com.sethukumar.OnlineExaminationSystem.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return repo.findByRole(role);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = getUser(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        if (user.getPassword() != null) {
            existing.setPassword(user.getPassword());
        }
        existing.setRole(user.getRole());
        return repo.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
