package org.example.service;

import org.example.model.User;
import org.example.model.request.CreateUserRequest;
import org.example.util.KeyGenerationUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * The type UserServiceImpl
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class UserServiceImpl implements UserService {

    private final Map<String, User> users;

    public UserServiceImpl() {
        users = new HashMap<>();
    }

    @Override
    public User createUser(CreateUserRequest request) {
        String userId = KeyGenerationUtil.generate();
        User user =
                User.builder().id(userId).name(request.getName()).email(request.getEmail()).build();
        users.put(userId, user);
        return users.get(userId);
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

}
