package org.example.model.request;

import lombok.Builder;
import lombok.Data;
import org.example.SplitType;
import org.example.model.User;

import java.util.List;
import java.util.Map;

/**
 * The type AddBillRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
public class AddBillRequest {
    private double amount;
    private SplitType splitType;
    private List<String> participants;
    private Map<String, Double> UserToPercentages;
    private Map<String, Double> UserToFixedAmounts;
    private String groupId;
    private User paidBy;
    // additional metadata like created by
}
