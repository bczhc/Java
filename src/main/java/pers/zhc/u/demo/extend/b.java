package pers.zhc.u.demo.extend;

class Cat extends Animal {
    void eat() {
        System.out.println("cat.eat");
    }

    public static class Test {
        public static void main(String[] args) {
            Animal a = new Animal();
        }
    }
}