package pers.zhc.u.demo.extend;

class Animal {
    void eat() {
        System.out.println("Animal.eat");
    }
}

class Dog extends Animal {
    void eat() {
        System.out.println("Dog.eat");
    }

    void t() {
        this.eat();
        super.eat();
        eat();
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Animal();
        a.eat();
        Dog d = new Dog();
        d.t();
    }
}