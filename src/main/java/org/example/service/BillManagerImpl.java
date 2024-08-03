package org.example.service;

import lombok.Data;
import org.example.model.Bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type BillManagerImpl
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
public class BillManagerImpl implements BillManager {

    private Map<String, Double> userAmounts;
    private Map<String, List<Bill>> groupStatements;

    private DivideStrategyFactory divideStrategyFactory;
    private GroupService groupService;

    public BillManagerImpl(DivideStrategyFactory divideStrategyFactory, GroupService groupService) {
        userAmounts = new HashMap<>();
        groupStatements = new HashMap<>();
        this.divideStrategyFactory = divideStrategyFactory;
        this.groupService = groupService;
    }

    @Override
    public void addBill(Bill request) {
        DivideStrategy strategy = divideStrategyFactory.getStrategy(request.getSplitType());
        Map<String, Double> balancesPerUser = strategy.calculateBalances(request);
        for (String userId : balancesPerUser.keySet()) {
            double updatedBalance =
                    userAmounts.getOrDefault(userId, 0.0) + balancesPerUser.get(userId);
            userAmounts.put(userId, updatedBalance);
        }

        // Update statements as well
        List<Bill> bills =
                groupStatements.getOrDefault(request.getGroupId(), new ArrayList<>());
        bills.add(request);
        groupStatements.put(request.getGroupId(), bills);

        // update balances in the group
        groupService.updateBalances(request.getGroupId(), balancesPerUser);
    }

    public List<Bill> getBillStatementsPerGroup(String groupId) {
        if (groupStatements.containsKey(groupId))
            return this.groupStatements.get(groupId);
        else {
            return new ArrayList<>();
        }
    }

    public Map<String, Double> getUserBalances() {
        return this.getUserAmounts();
    }

}
