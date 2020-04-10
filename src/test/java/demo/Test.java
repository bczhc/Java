package demo;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Number number = new Number();
        Thread t11 = new Thread(new T11(number));  //生成线程
        Thread t22 = new Thread(new T22(number));
        t11.setName("t11");  //设置线程名
        t22.setName("t22");
        t22.start();
        t11.start();   //启动线程

    }

    @org.junit.jupiter.api.Test
    void test() throws InterruptedException {
        final int[] l = new int[0];
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 start");
                synchronized (l) {
                    Thread.sleep(1000);
                    System.out.println("t1 get lock");
                }
                System.out.println("t1 release lock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("t2 start");
                synchronized (l) {
                    System.out.println("t2 get lock");
                }
                System.out.println("t2 release lock");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}

class Number {
    public int num = 1; //number.num的起始值为1
}

class T11 implements Runnable {
    private final Number number;

    T11(Number number) {    //通过构造函数引入 Number对象
        this.number = number;
    }

    public void run() {
        while (number.num < 30) { // 条件  number.num <30
            synchronized (number) {//通过同步语句块将异步线程变成同步线程执行以下语句，对象为number
                try {
                    if (number.num % 2 != 0) { //打印非2的倍数
                        System.out.println(Thread.currentThread().getName() + "------>" + number.num); //获取当前线程名
                        number.num++; //打印后num 自增
                    }
                    if (number.num % 2 == 0) {
                        number.notify(); //唤醒 休眠状态下的线程T22
                        number.wait();    //线程T11释放对象锁,让线程T22获取到对象锁,而线程T11进入休眠状态
                    }
                } catch (Exception e) {
                    e.printStackTrace(); //详细的异常情况
                }
            }
        }
    }
}

class T22 implements Runnable {
    private final Number number;

    T22(Number number) {
        this.number = number;
    }

    public void run() {
        while (number.num < 30) {
            synchronized (number) {
                try {
                    if (number.num % 2 == 0) {  //打印2的倍数
                        System.out.println(Thread.currentThread().getName() + "------>" + number.num);
                        number.num++;
                    }
                    if (number.num % 2 != 0) {
                        number.notify();
                        number.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}