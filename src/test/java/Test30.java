import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @author bczhc
 */
public class Test30 {
    @Test
    void test1() {
        String s = "(()))";
        StringBuilder sb = new StringBuilder(s);
        Stack<Character> stack = new Stack<>();
        final int length = s.length();
        for (int i = 0; i < length; i++) {
            final char c = s.charAt(i);
            if (stack.empty() && c == ')') sb.insert(0, '(');
            if (c == '(') stack.push(c);
            else if (!stack.empty() && c == ')') stack.pop();
        }
        while (!stack.empty()) {
            sb.append(')');
            stack.pop();
        }
        System.out.println(sb.toString());
    }

    @Test
    void test2() {
    }
}