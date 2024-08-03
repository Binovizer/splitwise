package org.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.example.SplitType;

import java.util.List;
import java.util.Map;

/**
 * The type Bill
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
@ToString
public class Bill {
    private double amount;
    private SplitType splitType;
    private List<String> participants;
    private Map<String, Double> UserToPercentages;
    private Map<String, Double> UserToFixedAmounts;
    private String groupId;
    private User paidBy;
    // created, updated at date fields if required
}
