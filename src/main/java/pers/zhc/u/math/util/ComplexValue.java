package pers.zhc.u.math.util;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class ComplexValue {
    public double re, im;

    public ComplexValue(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static void main(String[] args) {
        ComplexValue complexValue = new ComplexValue(3, 4);
        System.out.println(complexValue.multiply(new ComplexValue(4, 5)));
    }

    public double getComplexModule() {
        return Math.sqrt(Math.pow(re, 2D) + Math.pow(im, 2D));
    }

    public ComplexValue add(ComplexValue cv) {
        return new ComplexValue(this.re + cv.re, this.im + cv.im);
    }

    public ComplexValue add(double re, double im) {
        return new ComplexValue(this.re + re, this.im + im);
    }

    public ComplexValue selfAdd(ComplexValue cv) {
        this.re += cv.re;
        this.im += cv.im;
        return this;
    }

    public ComplexValue selfAdd(double re, double im) {
        this.re += re;
        this.im += im;
        return this;
    }

    public ComplexValue subtract(ComplexValue cv) {
        return new ComplexValue(this.re - cv.re, this.im - cv.im);
    }

    public ComplexValue subtract(double re, double im) {
        return new ComplexValue(this.re - re, this.im - im);
    }

    public ComplexValue selfSubtract(ComplexValue cv) {
        this.re -= cv.re;
        this.im -= cv.im;
        return this;
    }

    public ComplexValue selfSubtract(double re, double im) {
        this.re -= re;
        this.im -= im;
        return this;
    }

    public ComplexValue multiply(ComplexValue cv) {
        return new ComplexValue(this.re * cv.re - this.im * cv.im, this.re * cv.im + cv.re * this.im);
    }

    public ComplexValue multiply(double re, double im) {
        return new ComplexValue(this.re * re - this.im * im, this.re * im + re * this.im);
    }

    public ComplexValue selfMultiply(ComplexValue cv) {
        this.re = this.re * cv.re - this.im * cv.im;
        this.im = this.re * cv.im + cv.re * this.im;
        return this;
    }

    public ComplexValue selfMultiply(double re, double im) {
        this.re = this.re * re - this.im * im;
        this.im = this.re * im + re * this.im;
        return this;
    }

    public ComplexValue divide(ComplexValue cv) {
        double a = Math.pow(cv.re, 2D) + Math.pow(cv.im, 2D);
        return new ComplexValue((this.re * cv.re + this.im * cv.im) / a, (cv.re * this.im - this.re * cv.im) / a);
    }

    public ComplexValue divide(double re, double im) {
        double a = Math.pow(re, 2D) + Math.pow(im, 2D);
        return new ComplexValue((this.re * re + this.im * im) / a, (re * this.im - this.re * im) / a);
    }

    public ComplexValue selfDivide(ComplexValue cv) {
        double a = Math.pow(cv.re, 2D) + Math.pow(cv.im, 2D);
        this.re = (this.re * cv.re + this.im * cv.im) / a;
        this.im = (cv.re * this.im - this.re * cv.im) / a;
        return this;
    }

    public ComplexValue selfDivide(double re, double im) {
        double a = Math.pow(re, 2D) + Math.pow(im, 2D);
        this.re = (this.re * re + this.im * im) / a;
        this.im = (re * this.im - this.re * im) / a;
        return this;
    }

    @Override
    public String toString() {
        if (this.re == 0 && this.im == 0) return String.valueOf(0);
        else if (this.re == 0) return this.im + "i";
        else if (this.im == 0) return String.valueOf(re);
        else if (im > 0) return re + "+" + im + "i";
        else if (im < 0) return re + "" + im + "i";
        return String.valueOf(0);
    }

    public ComplexValue setValue(ComplexValue cv) {
        this.re = cv.re;
        this.im = cv.im;
        return this;
    }

    public ComplexValue setValue(double re, double im) {
        this.re = re;
        this.im = im;
        return this;
    }
}