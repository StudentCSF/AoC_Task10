import java.util.*;

public class Main {

    public static void main(String[] args) {
        task102();
    }

    static void task102() {
        Scanner in = new Scanner(System.in);

        Map<Character, KeyValue<Character, Integer>> oc = new HashMap<>();
        oc.put('[', new KeyValue<>(']', 2));
        oc.put('{', new KeyValue<>('}', 3));
        oc.put('(', new KeyValue<>(')', 1));
        oc.put('<', new KeyValue<>('>', 4));

        List<Stack<Character>> list = new ArrayList<>();
        Stack<Character> stack;
        while (true) {
            String string = in.nextLine();
            if (string.length() < 5) break;
            stack = new Stack<>();
            boolean flag = true;
            for (int i = 0; i < string.length(); i++) {
                char curr = string.charAt(i);
                if (oc.containsKey(curr)) {
                    stack.push(curr);
                } else {
                    if (oc.get(stack.peek()).k == curr) stack.pop();
                    else {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) list.add(stack);
        }
        long[] values = new long[list.size()];
        int k = 5;
        for (int i = 0; i < values.length; i++) {
            stack = list.get(i);
            while (!stack.empty()) {
                values[i] = k * values[i] + oc.get(stack.pop()).v;
            }
        }
        Arrays.sort(values);
        System.out.println(values[values.length / 2]);
    }

    static void task10() {
        Scanner in = new Scanner(System.in);

        Map<Character, Character> oc = new HashMap<>();
        oc.put('[', ']');
        oc.put('{', '}');
        oc.put('(', ')');
        oc.put('<', '>');
        Map<Character, Integer> cv = new HashMap<>();
        cv.put(')', 3);
        cv.put(']', 57);
        cv.put('}', 1197);
        cv.put('>', 25137);

        long sum = 0L;
        Stack<Character> stack;
        while (true) {
            String string = in.nextLine();
            if (string.length() < 5) break;
            stack = new Stack<>();
            for (int i = 0; i < string.length(); i++) {
                char curr = string.charAt(i);
                if (oc.containsKey(curr)) {
                    stack.push(curr);
                } else {
                    if (oc.get(stack.peek()) == curr) stack.pop();
                    else {
                        sum += cv.get(curr);
                        break;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    static class KeyValue<K, V> {
        K k;
        V v;

        public KeyValue(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValue<?, ?> keyValue = (KeyValue<?, ?>) o;
            return Objects.equals(k, keyValue.k) && Objects.equals(v, keyValue.v);
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, v);
        }
    }
}
