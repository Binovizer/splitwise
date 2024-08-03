package org.example.service;

import org.example.model.User;
import org.example.model.request.CreateUserRequest;

/**
 * The type UserService
 *
 * @author nadeem
 * Date : 03/08/24
 */
public interface UserService {
    User createUser(CreateUserRequest request);

    User getUser(String userId);
}
