package org.example.service;

import org.example.model.Bill;
import org.example.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type PercentageDivideStrategy
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class PercentageDivideStrategy implements DivideStrategy {
    @Override
    public Map<String, Double> calculateBalances(Bill bill) {
        List<String> participants = bill.getParticipants();
        double amount = bill.getAmount();
        Map<String, Double> userToPercentages = bill.getUserToPercentages();
        Map<String, Double> amounts = new HashMap<>();
        for (String userId : participants) {
            double totalAmount = 0;
            if (bill.getPaidBy().getId().equals(userId)) {
                totalAmount += amount;
            }
            Double percentage = userToPercentages.get(userId);
            double hisShare = amount * percentage / 100;
            totalAmount = totalAmount - hisShare;
            amounts.put(userId, totalAmount);
        }
        return amounts;
    }

    public static void main(String[] args) {
        PercentageDivideStrategy equalDivideStrategy = new PercentageDivideStrategy();
        Map<String, Double> userToPercentage = new HashMap<>();
        userToPercentage.put("1", 20.0);
        userToPercentage.put("2", 70.0);
        userToPercentage.put("3", 10.0);
        Map<String, Double> stringDoubleMap = equalDivideStrategy.calculateBalances(Bill.builder()
                .amount(120)
                .paidBy(User.builder().id("1").build())
                .participants(Arrays.asList("1", "2", "3"))
                .UserToPercentages(userToPercentage)
                .build());
        System.out.println("stringDoubleMap = " + stringDoubleMap);
    }
}
