package demo;

public class Demo1 {
    public static void main(String[] args) {
        Demo1_1 d = new Demo1_1();
        try {
            d.m();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done.");
    }
}

class Demo1_1 {
    void m() {
        Integer.parseInt("a");
    }
}
