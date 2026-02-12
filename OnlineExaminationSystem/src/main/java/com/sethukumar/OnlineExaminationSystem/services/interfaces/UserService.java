package com.sethukumar.OnlineExaminationSystem.services.interfaces;

import com.sethukumar.OnlineExaminationSystem.models.Role;
import com.sethukumar.OnlineExaminationSystem.models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    List<User> getUsersByRole(Role role);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
