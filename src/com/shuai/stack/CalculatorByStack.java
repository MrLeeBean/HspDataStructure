package com.shuai.stack;


import java.util.Scanner;

/**
 * 栈实现综合计算器
 */
public class CalculatorByStack {
    public static void main(String[] args) {

        String expression = "7*2*2-5+1-5+3-4";
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
            if (CalculatorUtil.isOperator(ch)) {//是运算符
                if (opeStack.isEmpty()) {//符号栈空
                    opeStack.push(ch);//运算符直接入栈
                } else {//符号栈非空
                    // 当前扫描到的运算符的优先级 >  栈中的运算符。
                    // 则将运算符直接入符号栈.
                    if (CalculatorUtil.getPriority(ch) > CalculatorUtil.getPriority(opeStack.peek())) {
                        opeStack.push(ch);
                    } else {
                        // 当前扫描到的的运算符的优先级 <= 栈中的运算符优先级。
                        // 则从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算。将得到结果入数栈。然后将当前扫描到的运算符入符号栈。
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

                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量字符串keepNum，用于拼接

                // 如果index是expression的最后一位 或者 index的后一位是运算符。则直接将keepNum压到numStack栈
                if (index == expression.length() - 1 || CalculatorUtil.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";//keepNum清空
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 当表达式扫描完毕，就按顺序地从数栈和符号栈中pop出相应的数和符号，并执行运算。
        while (!opeStack.isEmpty()) {
            num1 = numStack.pop();//后入的
            num2 = numStack.pop();//先入的
            ope = opeStack.pop();
            int res = CalculatorUtil.calculate(num2, num1, ope);
            numStack.push(res);
        }

        // 最后在数栈只有一个数字，就是表达式的结果
        return numStack.peek();

    }
}
