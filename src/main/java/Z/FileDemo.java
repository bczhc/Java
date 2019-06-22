package Z;

import java.io.File;
import java.io.IOException;

public class FileDemo {


    //创建文件
    public void create(File file) {//create   创建
        if (!file.exists()) {//不存在
            try {
                System.out.println(file.createNewFile());
                System.out.println("文件已经创建");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }

    public static void main(String[] args) {
        FileDemo f = new FileDemo();
        File file = new File("C:\\胡志澳\\test.txt");
        f.create(file);
    }


}
