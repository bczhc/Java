package pers.zhc.u.common;


import pers.zhc.u.Power;

import java.math.BigDecimal;

public class StrDic {
    @SuppressWarnings("SpellCheckingInspection")
    public static String alphabetL = "abcdefghijklmnopqrstuvwxyz";
    @SuppressWarnings({"SpellCheckingInspection", "WeakerAccess"})
    public static String alphabetU = "ABCDEFGHIJKLMNOPQRSTYVWXYZ";
    @SuppressWarnings("WeakerAccess")
    public static String alphabetA = alphabetL + alphabetU;
    public static String Num = "0123456789";
    @SuppressWarnings("unused")
    public static String alphabetLAndNum = alphabetL + Num;
    @SuppressWarnings("unused")
    public static String alphabetUAndNum = alphabetU + Num;
    @SuppressWarnings("unused")
    public static String alphabetAAndNum = alphabetA + Num;
    private String s;
    private int len;
    private StrDicDo target;
    private char[] d;

    public StrDic(String sInDic, int length) {
        this.s = sInDic;
        this.len = length;
    }

    @SuppressWarnings("unused")
    public StrDic(char[] dic, int length) {
        this.d = dic;
        this.len = length;
    }

    public void Do(StrDicDo target) {
        this.target = target;
        if (this.d == null) {
            this.d = this.s.toCharArray();
        }
        int dicLen = this.d.length;
        int[] i = new int[this.len];
        BigDecimal n = new Power().power(dicLen, this.len);
        long l = (long) Math.pow(dicLen, this.len);
        String nString = n.toString();
        for (long j = 0; j < l; j++) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < this.len; k++) {
                sb.append(d[i[k]]);
            }
            this.D(sb.toString(), nString);
            for (int k = this.len - 1; k > 0; --k) {
                if ((i[k] += 1) == dicLen) {
                    i[k] = 0;
                    if (k != 1) continue;
                    i[k - 1] += 1;
                } else break;
            }
        }
    }

    private void D(String s, String n) {
        this.target.f(s, n);
    }
}