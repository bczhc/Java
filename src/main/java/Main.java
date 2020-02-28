import pers.zhc.u.groovy.YouDao;

public class Main {

    public static void main(String[] args) {
        System.out.println("YouDao.translate(\"hello\") = " + YouDao.translate("hello"));
    }

    private static enum E {
        A(1,2);

        E(int i, int j) {
        }
    }
}