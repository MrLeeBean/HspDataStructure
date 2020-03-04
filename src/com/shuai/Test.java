package com.shuai;

import com.shuai.stack.CalculatorUtil;

public class Test {

    public static void main(String[] args) {

        boolean operator = CalculatorUtil.isOperator('+');
        int priority = CalculatorUtil.getPriority('*');
        int calculate = CalculatorUtil.calculate(1, 2, '+');



        System.out.println(operator+"--"+priority+"--"+calculate);

    }
}



