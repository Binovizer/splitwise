package org.example.service;

import org.example.model.Bill;
import org.example.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type FixedDivideStrategy
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class FixedDivideStrategy implements DivideStrategy {
    @Override
    public Map<String, Double> calculateBalances(Bill bill) {
        List<String> participants = bill.getParticipants();
        double amount = bill.getAmount();
        Map<String, Double> userToFixedAmounts = bill.getUserToFixedAmounts();
        Map<String, Double> amounts = new HashMap<>();
        for (String userId : participants) {
            double totalAmount = 0;
            if (bill.getPaidBy().getId().equals(userId)) {
                totalAmount += amount;
            }
            totalAmount = totalAmount - userToFixedAmounts.get(userId);
            amounts.put(userId, totalAmount);
        }
        return amounts;
    }

    public static void main(String[] args) {
        FixedDivideStrategy equalDivideStrategy = new FixedDivideStrategy();
        Map<String, Double> userToFixedAmounts = new HashMap<>();
        userToFixedAmounts.put("1", 20.0);
        userToFixedAmounts.put("2", 70.0);
        userToFixedAmounts.put("3", 10.0);
        Map<String, Double> stringDoubleMap = equalDivideStrategy.calculateBalances(Bill.builder()
                .amount(100)
                .paidBy(User.builder().id("1").build())
                .participants(Arrays.asList("1", "2", "3"))
                .UserToFixedAmounts(userToFixedAmounts)
                .build());
        System.out.println("stringDoubleMap = " + stringDoubleMap);
    }
}
