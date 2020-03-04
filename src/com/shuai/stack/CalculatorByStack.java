package com.shuai.stack;


import java.util.Scanner;

/**
 * 栈实现综合计算器
 */
public class CalculatorByStack {
    public static void main(String[] args) {

        String expression = "7*2*2-5+1-5+3-4";
        int cal = cal(expression);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n|-----请输入运算表达式：键入exit退出程序-----|");
            String next = scanner.next();
            if ("exit".equals(next)) {
                System.out.println("程序已退出...");
                scanner.close();
                break;
            }
            try {
                System.out.println(next + " = " + cal(next));
            } catch (Exception e) {
                System.out.println("发生错误..." + e.toString());
            }
        }
    }

    /**
     * 输入的要计算的字符串
     *
     * @param expression
     * @return
     */
    public static int cal(String expression) {

        ArrayStack numStack = new ArrayStack(10);//数栈
        ArrayStack opeStack = new ArrayStack(10);//符号栈

        int num1 = 0;
        int num2 = 0;
        int ope = 0;

        int index = 0;//用于扫描
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepNum = "";//用于拼接多位数

        while (true) {
            //依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            if (CalculatorUtil.isOperator(ch)) {//是操作符
                if (opeStack.isEmpty()) {//栈空
                    opeStack.push(ch);//操作符直接入栈
                } else {//栈非空
                    if (CalculatorUtil.getPriority(ch) > CalculatorUtil.getPriority(opeStack.peek())) {
                        opeStack.push(ch);
                    } else {
                        num1 = numStack.pop();//后入的
                        num2 = numStack.pop();//先入的
                        ope = opeStack.pop();
                        int res = CalculatorUtil.calculate(num2, num1, ope);
                        numStack.push(res);
                        opeStack.push(ch);
                    }
                }
            } else {//是数
                keepNum += ch;
                if (index == expression.length() - 1 || CalculatorUtil.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {//到了最后一位
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (!opeStack.isEmpty()) {
            num1 = numStack.pop();//后入的
            num2 = numStack.pop();//先入的
            ope = opeStack.pop();
            int res = CalculatorUtil.calculate(num2, num1, ope);
            numStack.push(res);
        }

        return numStack.peek();

    }
}
