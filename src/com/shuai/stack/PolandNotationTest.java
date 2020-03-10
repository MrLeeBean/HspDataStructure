package com.shuai.stack;

import java.util.*;

/**
 * 逆波兰计算器
 */
public class PolandNotationTest {
    public static void main(String[] args) {

        String str = "1+((2+3)*4)-5";
        List<String> listFromInfixExpression = getListFromInfixExpression(str);

        System.out.println("中缀List = " + listFromInfixExpression);
        System.out.println("后缀List = " + switchInfixListToSuffixList(listFromInfixExpression));
        System.out.println("计算结果 = " + calculator(switchInfixListToSuffixList(listFromInfixExpression)));

    }


    /**
     * 对逆波兰表达式的运算
     *
     * @param suffixExpressionList 后缀List表达式
     * @return
     */
    public static int calculator(List<String> suffixExpressionList) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : suffixExpressionList) {
            if (item.matches("\\d+")) {//如果是数
                stack.push(item);
            } else {//如果是符号
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int resNum = 0;
                switch (item) {
                    case "+":
                        resNum = num1 + num2;
                        break;
                    case "-":
                        resNum = num1 - num2;
                        break;
                    case "*":
                        resNum = num1 * num2;
                        break;
                    case "/":
                        resNum = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符错误");
                }
                //运算后入栈
                stack.push(String.valueOf(resNum));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 中缀表达式转对应的List
     *
     * @param infixExpression 中缀String表达式
     * @return
     */
    public static List<String> getListFromInfixExpression(String infixExpression) {
        //定义一个List,存放中缀表达式 对应的内容
        List<String> list = new ArrayList<>();
        //指针，用于遍历 中缀表达式字符串
        int index = 0;
        //每遍历到一个字符，就放入到
        char ch = ' ';
        while (index < infixExpression.length()) {
            //不是数 注意：数的ASCII码范围：'0'[48]->'9'[57]
            if ((ch = infixExpression.charAt(index)) < 48 || (ch = infixExpression.charAt(index)) > 57) {
                list.add(ch + "");//直接加入list
                index++;
            } else {//是数
                String str = "";
                while (index < infixExpression.length()
                        && (ch = infixExpression.charAt(index)) >= 48
                        && (ch = infixExpression.charAt(index)) <= 57) {
                    str += ch;//多位数拼接
                    index++;
                }
                list.add(str);
            }
        }
        return list;
    }


    /**
     * 中缀表达式List转后缀表达式List
     *
     * @param infixList 中缀表达式List
     * @return 后缀表达式List
     */
    public static List<String> switchInfixListToSuffixList(List<String> infixList) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();// 符号栈
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        ArrayList<String> s2 = new ArrayList();// 储存中间结果的List s2
        for (String item : infixList) {
            if (item.matches("\\d+")) {// 数 --> 将其压s2；
                s2.add(item);
            } else if ("(".equals(item)) {// 左括号 --> 则直接压入s1
                s1.push(item);
            } else if (")".equals(item)) {// 右括号
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//丢弃栈顶的括号.即将 ( 弹出 s1栈， 消除小括号
            } else {// 运算符
                while (!s1.empty() && !"(".equals(s1.peek()) && getPriority(item) <= getPriority(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        //注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
        return s2;
    }

    /**
     * 获取运算符的优先级
     *
     * @param operator
     * @return
     */
    public static int getPriority(String operator) {

        if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        }
        if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        }
        System.out.println("不存在 " + operator + " 运算符");
        return 0;

    }
}
