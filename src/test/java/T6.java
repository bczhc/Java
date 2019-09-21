import java.io.*;
import java.math.BigDecimal;
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

class T18 {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        BigDecimal bd1 = new BigDecimal("123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235.23454123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235123112321312389757389892345894132423543423344789314783427898934892789354378975235981347789248794527324782343427941237890124379241378981347905718347252354235");
        BigDecimal bd2 = new BigDecimal("2134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912.21343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912142091288249");
//        System.out.println(bd1.add(bd2).toString().equals("2134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902389047823894673904586908991221343096098752903849023890478238946739045869089912213430960987529038490238904782389467390458690899122134309609875290384902512160145207063661976801337115475519642176248638338673906137881631835223468887449412308776777833017563687125817408628348815278363513290957781008732154743282947006995216976666381113688990231176447272368679793022374637980761879266592406196968876827739033432107171810818399928789246513550988856638290620736300178129797016908221380978732988444647036272567313823028261522473768024805002785339962765019838028153576916792172802118840360024433989116669366250294766171147028139710021312533732853488645249743156314021662291528360652517811148323750341908013726220252571638261917783473242550075547499999669626281370395729613021156941144114333664408488510293741369784610662490162390658404695641329895880318392869214140763737256639253218223904477737551113180509281630426805242579622934161345456466208420065293606413782824133079162589292434701528214388886463931279593283757625448738247939693649459091953303231622792513427065273655252492627338473697331221865225161413647868131292633178292633335558220275770956937425131694136768442626440038688370928836934657633812843626937948273915283568256896939690348452988026961345202867996336631391763222335756854305776843821918137839675178821769433926880469923558404402615167245816989061784798283828287972569248814446005258084147002799551336496435761435806345854504410873827805022332681324360179813069874358115657399124084912227171391251835911794711288232600324444004471251101378125601351268051435626479804559325093411072461835028219565817366325351480253501109545591223079633696916372606238101845824799115600584475080100809567371142832474069851367181479669603323306881661462033662249571704250818357625791911488688585111271732691637842351219456248014850229111467475340131885196929608825491300974168981411224603188350879874932622662448205734256705242118798057027098964254265620883736837072689201229369252419162580986223132145228005238383957293991400104213024534743350744918930836133037205932890735248005682550033244502664644763877625029171683922699114233773564771037336643028228265269459586852457393121444147"));
        System.out.println(bd1.add(bd2).toString());
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}

class T19 {
    int a;
}

class T20 extends T19 {

    public static void main(String[] args) {
        T20 t20 = new T20();
        t20.f();
        t20.a = 3;
        System.out.println("t20.a = " + t20.a);
        T19 t19 = new T19();
        t19.a = 4;
    }

    private void f() {
        a = 1;
    }
}