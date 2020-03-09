package com.shuai.stack;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 逆波兰
 */
public class PolandNotationTest {
    public static void main(String[] args) {

        //  4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
//        String str = "4 5 * 8 - 60 + 8 2 / +";
//        int result = calculator(getListFromSuffixExpression(str));
//        System.out.println(result);


        String str = "4*5-8+60+8/2";
        List<String> listFromInfixExpression = getListFromInfixExpression(str);

        System.out.println(listFromInfixExpression);

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
     * 后缀表达式转对应的List
     *
     * @param suffixExpression 后缀String表达式
     * @return
     */
    public static List<String> getListFromSuffixExpression(String suffixExpression) {
        return Arrays.asList(suffixExpression.split(" "));
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
}
