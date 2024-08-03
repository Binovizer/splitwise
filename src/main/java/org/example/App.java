package org.example;

import org.example.model.Bill;
import org.example.model.Group;
import org.example.model.User;
import org.example.model.request.AddUserToGroupRequest;
import org.example.model.request.CreateGroupRequest;
import org.example.model.request.CreateUserRequest;
import org.example.service.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User one = userService.createUser(CreateUserRequest.builder()
                .name("one").email("one@gmail.com").build()
        );
        System.out.println("one = " + one);
        User two = userService.createUser(CreateUserRequest.builder()
                .name("two").email("two@gmail.com").build()
        );
        System.out.println("two = " + two);
        User three = userService.createUser(CreateUserRequest.builder()
                .name("two").email("two@gmail.com").build()
        );
        System.out.println("three = " + three);

        GroupServiceImpl groupService = new GroupServiceImpl(userService);
        Group group = groupService.createGroup(CreateGroupRequest.builder()
                .name("G1")
                .description("Group 1")
                .build());
        System.out.println("group = " + group);
        groupService.addUserToGroup(
                AddUserToGroupRequest.builder()
                        .userId(one.getId())
                        .groupId(group.getId())
                        .build());
        groupService.addUserToGroup(
                AddUserToGroupRequest.builder()
                        .userId(two.getId())
                        .groupId(group.getId())
                        .build());
        groupService.addUserToGroup(
                AddUserToGroupRequest.builder()
                        .userId(three.getId())
                        .groupId(group.getId())
                        .build());
        System.out.println("group = " + group);

        Group group2 = groupService.createGroup(CreateGroupRequest.builder()
                .name("G2")
                .description("Group 2")
                .build());
        System.out.println("group = " + group2);
        groupService.addUserToGroup(
                AddUserToGroupRequest.builder()
                        .userId(one.getId())
                        .groupId(group2.getId())
                        .build());
        groupService.addUserToGroup(
                AddUserToGroupRequest.builder()
                        .userId(two.getId())
                        .groupId(group2.getId())
                        .build());
        System.out.println("group = " + group2);

        DivideStrategyFactory factory = new DivideStrategyFactory();
        BillManager billManager = new BillManagerImpl(factory, groupService);
        List<Bill> billStatementsPerGroup = billManager.getBillStatementsPerGroup(group.getId());
        System.out.println("billStatementsPerGroup = " + billStatementsPerGroup);

        // add Bill 1
        billManager.addBill(Bill.builder()
                .splitType(SplitType.EQUAL)
                .amount(100)
                .paidBy(User.builder().id(one.getId()).build())
                .participants(Arrays.asList(one.getId(), two.getId(), three.getId()))
                .groupId(group.getId())
                .build());

        Map<String, Double> fixedAmounts = new HashMap<>();
        fixedAmounts.put(one.getId(), 70.0);
        fixedAmounts.put(two.getId(), 30.0);
        billManager.addBill(Bill.builder()
                .splitType(SplitType.FIXED)
                .amount(100)
                .paidBy(User.builder().id(one.getId()).build())
                .participants(Arrays.asList(one.getId(), two.getId()))
                .groupId(group2.getId())
                .UserToFixedAmounts(fixedAmounts)
                .build());

        Map<String, Double> percentages = new HashMap<>();
        percentages.put(one.getId(), 25.0);
        percentages.put(two.getId(), 75.0);
        billManager.addBill(Bill.builder()
                .splitType(SplitType.PERCENTAGE)
                .amount(100)
                .paidBy(User.builder().id(one.getId()).build())
                .participants(Arrays.asList(one.getId(), two.getId()))
                .groupId(group2.getId())
                .UserToPercentages(percentages)
                .build());
        List<Bill> billStatementsPerGroupAfterBill = billManager.getBillStatementsPerGroup(group.getId());
        System.out.println("billStatementsPerGroupAfterBill = " + billStatementsPerGroupAfterBill);

        Map<String, Double> userBalances = billManager.getUserBalances();
        System.out.println("userBalances = " + userBalances);

        Map<String, Double> userAmounts = groupService.getUserAmounts(group.getId());
        System.out.println("userAmounts G1 = " + userAmounts);

        System.out.println("userAmounts G2 = " + groupService.getUserAmounts(group2.getId()));
    }
}
