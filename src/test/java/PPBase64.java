public class PPBase64 {

   // $FF: synthetic field
   static final boolean a;
   private static final byte[] b;
   private static final byte[] c;
   private static final byte[] d;
   private static final byte[] e;


   static {
      boolean var0;
      if(!PPBase64.class.desiredAssertionStatus()) {
         var0 = true;
      } else {
         var0 = false;
      }

      a = var0;
      b = new byte[]{(byte)65, (byte)66, (byte)67, (byte)68, (byte)69, (byte)70, (byte)71, (byte)72, (byte)73, (byte)74, (byte)75, (byte)76, (byte)77, (byte)78, (byte)79, (byte)80, (byte)81, (byte)82, (byte)83, (byte)84, (byte)85, (byte)86, (byte)87, (byte)88, (byte)89, (byte)90, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)43, (byte)47};
      c = new byte[]{(byte)65, (byte)66, (byte)67, (byte)68, (byte)69, (byte)70, (byte)71, (byte)72, (byte)73, (byte)74, (byte)75, (byte)76, (byte)77, (byte)78, (byte)79, (byte)80, (byte)81, (byte)82, (byte)83, (byte)84, (byte)85, (byte)86, (byte)87, (byte)88, (byte)89, (byte)90, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)45, (byte)95};
      d = new byte[]{(byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-5, (byte)-5, (byte)-9, (byte)-9, (byte)-5, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-5, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)62, (byte)-9, (byte)-9, (byte)-9, (byte)63, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)58, (byte)59, (byte)60, (byte)61, (byte)-9, (byte)-9, (byte)-9, (byte)-1, (byte)-9, (byte)-9, (byte)-9, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)26, (byte)27, (byte)28, (byte)29, (byte)30, (byte)31, (byte)32, (byte)33, (byte)34, (byte)35, (byte)36, (byte)37, (byte)38, (byte)39, (byte)40, (byte)41, (byte)42, (byte)43, (byte)44, (byte)45, (byte)46, (byte)47, (byte)48, (byte)49, (byte)50, (byte)51, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9};
      e = new byte[]{(byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-5, (byte)-5, (byte)-9, (byte)-9, (byte)-5, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-5, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)62, (byte)-9, (byte)-9, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)58, (byte)59, (byte)60, (byte)61, (byte)-9, (byte)-9, (byte)-9, (byte)-1, (byte)-9, (byte)-9, (byte)-9, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)63, (byte)-9, (byte)26, (byte)27, (byte)28, (byte)29, (byte)30, (byte)31, (byte)32, (byte)33, (byte)34, (byte)35, (byte)36, (byte)37, (byte)38, (byte)39, (byte)40, (byte)41, (byte)42, (byte)43, (byte)44, (byte)45, (byte)46, (byte)47, (byte)48, (byte)49, (byte)50, (byte)51, (byte)-9, (byte)-9, (byte)-9, (byte)-9, (byte)-9};
   }

   private static int a(byte[] var0, int var1, byte[] var2, int var3, byte[] var4) {
      if(var0[var1 + 2] == 61) {
         var2[var3] = (byte)((var4[var0[var1]] << 24 >>> 6 | var4[var0[var1 + 1]] << 24 >>> 12) >>> 16);
         return 1;
      } else if(var0[var1 + 3] == 61) {
         var1 = var4[var0[var1]] << 24 >>> 6 | var4[var0[var1 + 1]] << 24 >>> 12 | var4[var0[var1 + 2]] << 24 >>> 18;
         var2[var3] = (byte)(var1 >>> 16);
         var2[var3 + 1] = (byte)(var1 >>> 8);
         return 2;
      } else {
         var1 = var4[var0[var1]] << 24 >>> 6 | var4[var0[var1 + 1]] << 24 >>> 12 | var4[var0[var1 + 2]] << 24 >>> 18 | var4[var0[var1 + 3]] << 24 >>> 24;
         var2[var3] = (byte)(var1 >> 16);
         var2[var3 + 1] = (byte)(var1 >> 8);
         var2[var3 + 2] = (byte)var1;
         return 3;
      }
   }

   private static byte[] a(byte[] var0, int var1, int var2, byte[] var3, int var4, byte[] var5) {
      int var8 = 0;
      int var6;
      if(var2 > 0) {
         var6 = var0[var1] << 24 >>> 8;
      } else {
         var6 = 0;
      }

      int var7;
      if(var2 > 1) {
         var7 = var0[var1 + 1] << 24 >>> 16;
      } else {
         var7 = 0;
      }

      if(var2 > 2) {
         var8 = var0[var1 + 2] << 24 >>> 24;
      }

      var1 = var8 | var7 | var6;
      switch(var2) {
      case 1:
         var3[var4] = var5[var1 >>> 18];
         var3[var4 + 1] = var5[var1 >>> 12 & 63];
         var3[var4 + 2] = 61;
         var3[var4 + 3] = 61;
         return var3;
      case 2:
         var3[var4] = var5[var1 >>> 18];
         var3[var4 + 1] = var5[var1 >>> 12 & 63];
         var3[var4 + 2] = var5[var1 >>> 6 & 63];
         var3[var4 + 3] = 61;
         return var3;
      case 3:
         var3[var4] = var5[var1 >>> 18];
         var3[var4 + 1] = var5[var1 >>> 12 & 63];
         var3[var4 + 2] = var5[var1 >>> 6 & 63];
         var3[var4 + 3] = var5[var1 & 63];
         return var3;
      default:
         return var3;
      }
   }

   public static byte[] decode(String var0) throws PPBase64DecoderException {
      byte[] var1 = var0.getBytes();
      return decode(var1, 0, var1.length);
   }

   public static byte[] decode(byte[] var0) throws PPBase64DecoderException {
      return decode(var0, 0, var0.length);
   }

   public static byte[] decode(byte[] var0, int var1, int var2) throws PPBase64DecoderException {
      return decode(var0, var1, var2, d);
   }

   public static byte[] decode(byte[] var0, int var1, int var2, byte[] var3) throws PPBase64DecoderException {
      byte[] var9 = new byte[var2 * 3 / 4 + 2];
      byte[] var10 = new byte[4];
      int var7 = 0;
      int var6 = 0;
      int var5 = 0;

      while(true) {
         if(var7 < var2) {
            label66: {
               byte var4 = (byte)(var0[var7 + var1] & 127);
               byte var8 = var3[var4];
               if(var8 < -5) {
                  throw new PPBase64DecoderException("Bad Base64 input character at " + var7 + ": " + var0[var7 + var1] + "(decimal)");
               }

               int var12;
               if(var8 >= -1) {
                  if(var4 == 61) {
                     var12 = var2 - var7;
                     byte var11 = (byte)(var0[var2 - 1 + var1] & 127);
                     if(var6 == 0 || var6 == 1) {
                        throw new PPBase64DecoderException("invalid padding byte \'=\' at byte offset " + var7);
                     }

                     if(var6 == 3 && var12 > 2 || var6 == 4 && var12 > 1) {
                        throw new PPBase64DecoderException("padding byte \'=\' falsely signals end of encoded value at offset " + var7);
                     }

                     if(var11 != 61 && var11 != 10) {
                        throw new PPBase64DecoderException("encoded value has invalid trailing byte");
                     }
                     break label66;
                  }

                  var12 = var6 + 1;
                  var10[var6] = var4;
                  if(var12 == 4) {
                     var6 = a(var10, 0, var9, var5, var3) + var5;
                     var5 = 0;
                  } else {
                     var6 = var5;
                     var5 = var12;
                  }
               } else {
                  var12 = var5;
                  var5 = var6;
                  var6 = var12;
               }

               var12 = var7 + 1;
               var7 = var6;
               var6 = var5;
               var5 = var7;
               var7 = var12;
               continue;
            }
         }

         var1 = var5;
         if(var6 != 0) {
            if(var6 == 1) {
               throw new PPBase64DecoderException("single trailing character at offset " + (var2 - 1));
            }

            var10[var6] = 61;
            var1 = var5 + a(var10, 0, var9, var5, var3);
         }

         var0 = new byte[var1];
         System.arraycopy(var9, 0, var0, 0, var1);
         return var0;
      }
   }

   public static byte[] decodeWebSafe(String var0) throws PPBase64DecoderException {
      byte[] var1 = var0.getBytes();
      return decodeWebSafe(var1, 0, var1.length);
   }

   public static byte[] decodeWebSafe(byte[] var0) throws PPBase64DecoderException {
      return decodeWebSafe(var0, 0, var0.length);
   }

   public static byte[] decodeWebSafe(byte[] var0, int var1, int var2) throws PPBase64DecoderException {
      return decode(var0, var1, var2, e);
   }

   public static String encode(byte[] var0) {
      return encode(var0, 0, var0.length, b, true);
   }

   public static String encode(byte[] var0, int var1, int var2, byte[] var3, boolean var4) {
      var0 = encode(var0, var1, var2, var3, Integer.MAX_VALUE);

      for(var1 = var0.length; !var4 && var1 > 0 && var0[var1 - 1] == 61; --var1) {
         ;
      }

      return new String(var0, 0, var1);
   }

   public static byte[] encode(byte[] var0, int var1, int var2, byte[] var3, int var4) {
      int var5 = (var2 + 2) / 3 * 4;
      byte[] var10 = new byte[var5 + var5 / var4];
      int var6 = 0;
      var5 = 0;

      int var7;
      int var8;
      for(var7 = 0; var7 < var2 - 2; var5 = var8 + 4) {
         var8 = var0[var7 + var1] << 24 >>> 8 | var0[var7 + 1 + var1] << 24 >>> 16 | var0[var7 + 2 + var1] << 24 >>> 24;
         var10[var5] = var3[var8 >>> 18];
         var10[var5 + 1] = var3[var8 >>> 12 & 63];
         var10[var5 + 2] = var3[var8 >>> 6 & 63];
         var10[var5 + 3] = var3[var8 & 63];
         int var9 = var6 + 4;
         var6 = var9;
         var8 = var5;
         if(var9 == var4) {
            var10[var5 + 4] = 10;
            var8 = var5 + 1;
            var6 = 0;
         }

         var7 += 3;
      }

      var8 = var5;
      if(var7 < var2) {
         a(var0, var7 + var1, var2 - var7, var10, var5, var3);
         var1 = var5;
         if(var6 + 4 == var4) {
            var10[var5 + 4] = 10;
            var1 = var5 + 1;
         }

         var8 = var1 + 4;
      }

      if(!a && var8 != var10.length) {
         throw new AssertionError();
      } else {
         return var10;
      }
   }

   public static String encodeWebSafe(byte[] var0, boolean var1) {
      return encode(var0, 0, var0.length, c, var1);
   }
}
