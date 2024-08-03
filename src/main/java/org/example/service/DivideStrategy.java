package org.example.service;

import org.example.model.Bill;
import org.example.model.request.AddBillRequest;

import java.util.Map;

/**
 * The type DivideStrategy
 *
 * @author nadeem
 * Date : 03/08/24
 */
public interface DivideStrategy {

    Map<String, Double> calculateBalances(Bill bill);
}
