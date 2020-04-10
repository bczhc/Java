import pers.zhc.u.ComplexDefinite;
import pers.zhc.u.math.util.ComplexFunctionInterface;
import pers.zhc.u.math.util.ComplexValue;

/**
 * @author bczhc
 */
public class FS {
    public static void main(String[] args) {
        double T = 50, omega = 2 * Math.PI / T;
        F f = new F();
        f.omega = omega;
        f.n = 1;
        ComplexDefinite integral = new ComplexDefinite();
        integral.n = 10000;
        final ComplexValue r = integral.getDefiniteIntegralByTrapezium(0, T, f).selfDivide(T, 0);
        System.out.println("r = " + r);
    }

    static class F implements ComplexFunctionInterface {
        public double n, omega;

        @Override
        public ComplexValue x(double t) {
            ComplexValue a = new ComplexValue(0, 0);
            ComplexValue b = new ComplexValue(0, 0);
            b.setValue(10, 10);
            a.setValue(10, 10);
            a = a.selfMultiply(Math.cos(-n * omega * t), Math.sin(-n * omega * t));
            b = b.multiply(Math.cos(-n * omega * t), Math.sin(-n * omega * t));
            System.out.println(a.equals(b));
            return a;
        }
    }
}
