package org.example.service;

import org.example.model.Group;
import org.example.model.request.AddUserToGroupRequest;
import org.example.model.request.CreateGroupRequest;

import java.util.Map;

/**
 * The type GroupService
 *
 * @author nadeem
 * Date : 03/08/24
 */
public interface GroupService {

    Group createGroup(CreateGroupRequest request);

    void addUserToGroup(AddUserToGroupRequest request);

    void updateBalances(String groupId, Map<String, Double> userAmounts);

    Map<String, Double> getUserAmounts(String groupId);
}
