package org.example.service;

import org.example.model.Bill;

import java.util.List;
import java.util.Map;

/**
 * The type BillManager
 *
 * @author nadeem
 * Date : 03/08/24
 */
public interface BillManager {

    void addBill(Bill request);

    List<Bill> getBillStatementsPerGroup(String groupId);

    Map<String, Double> getUserBalances();
}
