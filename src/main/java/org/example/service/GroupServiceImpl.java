package org.example.service;

import org.example.model.Group;
import org.example.model.User;
import org.example.model.request.AddUserToGroupRequest;
import org.example.model.request.CreateGroupRequest;
import org.example.util.KeyGenerationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type GroupServiceImpl
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class GroupServiceImpl implements GroupService {

    // In memory databases
    private final Map<String, Group> groups;
    private final Map<String, Map<String, Double>> groupIdToUserAmounts;

    // Dependencies
    private final UserService userService;

    public GroupServiceImpl(UserService userService) {
        this.groups = new HashMap<>();
        this.groupIdToUserAmounts = new HashMap<>();
        this.userService = userService;
    }

    @Override
    public Group createGroup(CreateGroupRequest request) {
        String groupId = KeyGenerationUtil.generate();
        Group group = Group.builder()
                .id(groupId)
                .name(request.getName())
                .description(request.getDescription())
                .members(new ArrayList<>())
                .build();
        groups.put(groupId, group);
        return groups.get(groupId);
    }

    @Override
    public void addUserToGroup(AddUserToGroupRequest request) {
        String groupId = request.getGroupId();
        Group group = groups.get(groupId);
        User userToBeAdded = userService.getUser(request.getUserId());
        group.getMembers().add(userToBeAdded);
    }

    @Override
    public void updateBalances(String groupId, Map<String, Double> userAmounts) {
        Map<String, Double> userAmountsMap = groupIdToUserAmounts.getOrDefault(groupId, new HashMap<>());
        for (String userId : userAmounts.keySet()) {
            Double newAdditionalAmount = userAmounts.get(userId);
            Double existingBalance = userAmountsMap.getOrDefault(userId, 0.0);
            userAmountsMap.put(userId, existingBalance + newAdditionalAmount);
        }
        groupIdToUserAmounts.put(groupId, userAmountsMap);
    }

    @Override
    public Map<String, Double> getUserAmounts(String groupId) {
        return this.groupIdToUserAmounts.get(groupId);
    }
}
