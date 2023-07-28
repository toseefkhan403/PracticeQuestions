package com.practice.stack;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("({[]})"));
    }

    // use stack - push when starting bracket - false if popped doesnt match opening
    // or if stack is empty - valid if stack is empty in the end - O(n), O(n)
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char i : s.toCharArray()) {
            switch (i) {
                case '(':
                case '{':
                case '[':
                    stack.push(i);
                    break;

                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;

                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;

                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }

}
