package com.sxw.code.lambda.countapple.v4;

import com.sxw.code.lambda.countapple.Apple;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-06-16
 * Time: 8:24
 *
 * @author shixiangweii
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}