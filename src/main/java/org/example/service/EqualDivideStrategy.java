package org.example.service;

import org.example.SplitType;
import org.example.model.Bill;
import org.example.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type EqualDivideStrategy
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class EqualDivideStrategy implements DivideStrategy {
    @Override
    public Map<String, Double> calculateBalances(Bill bill) {
        List<String> participants = bill.getParticipants();
        double amount = bill.getAmount();
        double perUserShare = amount / participants.size();
        Map<String, Double> amounts = new HashMap<>();
        for (String userId : participants) {
            double totalAmount = 0;
            if (bill.getPaidBy().getId().equals(userId)) {
                totalAmount += amount;
            }
            totalAmount = totalAmount - perUserShare;
            amounts.put(userId, totalAmount);
        }
        return amounts;
    }

    public static void main(String[] args) {
        EqualDivideStrategy equalDivideStrategy = new EqualDivideStrategy();
        Map<String, Double> stringDoubleMap = equalDivideStrategy.calculateBalances(Bill.builder()
                .amount(100)
                .splitType(SplitType.EQUAL)
                .paidBy(User.builder().id("1").build())
                .participants(Arrays.asList("1", "2", "3"))
                .build());
        System.out.println("stringDoubleMap = " + stringDoubleMap);
    }
}
