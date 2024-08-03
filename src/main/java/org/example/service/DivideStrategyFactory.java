package org.example.service;

import org.example.SplitType;

/**
 * The type StrategyFactory
 *
 * @author nadeem
 * Date : 03/08/24
 */
public class DivideStrategyFactory {
    public DivideStrategy getStrategy(SplitType splitType) {
        if (splitType == SplitType.PERCENTAGE) {
            return new PercentageDivideStrategy();
        } else if (splitType == SplitType.FIXED) {
            return new FixedDivideStrategy();
        } else {
            return new EqualDivideStrategy();
        }
    }
}
