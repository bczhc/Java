package z;

import pers.zhc.u.Base128;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckRec {
    public static void main(String[] args) {
        CheckRec o = new CheckRec();
        int length = args.length;
        if (length == 0) {
            o.h();
            return;
        }
        for (String arg : args) {
            if (arg.equals("/?") || arg.equals("-h") || arg.equals("-?") || arg.equals("--help")) {
                o.h();
                return;
            }
        }
        List<String> exceptList = new ArrayList<>(Arrays.asList(args).subList(3, length));
        int a = Integer.parseInt(args[1]);
        int b = Integer.parseInt(args[2]);
        for (int i = a; i <= b; i++) {
            String strLenTo = new Base128(String.valueOf(i)).NumStr_lenTo(4);
            File file = new File(args[0] + "/REC" + strLenTo + ".mp3");
            if (!file.exists()) {
                String name = file.getName();
                int index = name.lastIndexOf('.');
                String s = String.valueOf(Integer.parseInt(name.substring(3, index)));
                boolean b1 = o.has(s, exceptList);
                if (!b1) {
                    System.out.println(name.substring(0, index));
                }
            }
        }
    }

    private boolean has(String s, List<String> l) {
        for (String s1 : l) {
            if (s.equals(s1)) {
                return true;
            }
        }
        return false;
    }

    private void h() {
        System.out.println("命令 [目录路径] [起] [讫] [排除...]");
    }
}