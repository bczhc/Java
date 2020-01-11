package z;

import java.io.File;

public class TwoFilesXor {
    public static void main(String[] args) {
        File f1 = new File("C:\\Users\\zhc-2\\Downloads\\Something_Just_Like_This-The_Chainsmokers_Coldplay-23540977.kwm");
        File f2 = new File("C:\\Users\\zhc-2\\Downloads\\The Chainsmokers_Coldplay-Something Just Like This.flac");
        long f1L = f1.length();
        long f2L = f2.length();
        long dL = f1L > f2L ? f1L : f2L;
    }
}