package s;/*
package s;

public class A {
    public static void main(String[] args) {
        A a = new A();
        Runnable runnable = a.getRunnable(1);
        System.out.println("runnable.equals(new Runnable() {\n            @Override\n            public void run() {\n                System.out.println(1);\n            }\n        }) = " + runnable.equals(new Runnable() {
            @Override
            public void run() {
                if (1 == 1) {
                    System.out.println(1);
                } else {
                    System.out.println(".");
                }
            }
        }));
    }

    private Runnable getRunnable(int o) {
        return new Runnable() {
            @Override
            public void run() {
                if (o == 1) {
                    System.out.println(1);
                } else {
                    System.out.println(".");
                }
            }
        };
    }
}*/