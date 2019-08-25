import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

class T9 {
    int a = 0;
}

class T6 extends T9 {
    void f() {
        this.a = 1;
    }
}

class T8 extends T9 {
    void f() {
        System.out.println("this.a = " + this.a);
        this.a = 2;
        System.out.println("this.a = " + this.a);
    }
}

class T7 {
    public static void main(String[] args) {
        new T6().f();
        new T8().f();
    }
}

class T10 {
    public static void main(String[] args) throws IOException {
        File f = new File("/home/zhc/z/\\r.txt");
        /*OutputStream os = new FileOutputStream(f, false);
        os.write(new byte[]{97, 0x0D, 97});
        os.close();*/
        InputStream is = new FileInputStream(f);
        byte[] bytes = new byte[10];
        System.out.println("is.read(bytes) = " + is.read(bytes));
        System.out.println(Arrays.toString(bytes));
        is.close();
    }
}

class T11 {
    public static void main(String[] args) {
        new T11().b();
    }

    private void b() {
        int[] a = new int[]{3, 4, 5};
        int[] b = a(a);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
    }

    private int[] a(int[] ints) {
        ints[0] = 1;
        return ints;
    }
}

class T12 {
    public static void main(String[] args) {
        byte[] bytes = {23, 108, 61, 15, 11, 9, 57, 124, 0};
        System.out.println("Base64.getEncoder().encode(bytes) = " + Arrays.toString(Base64.getEncoder().encode(bytes)));
    }
}

abstract class DefiniteIntegralApp {

    private double sum;
    private double e;

    // 定义被积函数，可以修改
    abstract double f(double x);

    // 定义第i个区间的中点值，即定义积分变量
    private double zhongjian(double a, double b, double n, int i) {
        return a + i * (b - a) / n;
    }

    // 定义每个小区间的间隔差，即将范围分成n个等区间
    private double cha(double a, double b, double n) {
        return (b - a) / n;
    }

    DefiniteIntegralApp(double a, double b, int n) {
        e = cha(a, b, ((double) n));
        // 求和，循环从第一个区间叠加到第10000个
        for (int j = 1; j <= ((double) n); j++) {
            double x = zhongjian(a, b, ((double) n), j);
            sum += f(x);

        }
    }

    double get() {
        return this.sum * e;
    }
}

class T13 {
    public static void main(String[] args) {
        double d = new DefiniteIntegralApp(1, 10, 100000000) {
            @Override
            double f(double x) {
//                if (x == 1) return x;
//                return 0;
                return x * x;
            }
        }.get();
        System.out.println("d = " + d);
    }
}

class T14 {
    public static void main(String[] args) {
        new T14().m();
    }

    private void m() {
    }

}

class T15 {
    public static void main(String[] args) {
        byte[] bytes = {(byte) 143, 59, 47, 25, 62, 33, (byte) 214};
        System.out.println("Base64.getEncoder().encode(bytes) = " + Arrays.toString(Base64.getEncoder().encode(bytes)));
    }
}

class T16 {
    public static void main(String[] args) {
    }
}