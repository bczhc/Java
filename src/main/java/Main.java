/**
 * @author bczhc
 */
public class Main {
    static {
        System.out.println("1");
    }

    public static void m1() {
    }


    public static class C2 {
        static {
            System.out.println("2");
        }

        public static void m2() {
        }

    }
}

class C3 {
    public static void main(String[] args) {
        Main.m1();
        Main.C2.m2();
    }
}