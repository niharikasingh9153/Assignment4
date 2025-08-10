package com.smartbank.utils;

@FunctionalInterface
public interface InterestCalculator {
    double calculate(double principal, double rate, int years);
}