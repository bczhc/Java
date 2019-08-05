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