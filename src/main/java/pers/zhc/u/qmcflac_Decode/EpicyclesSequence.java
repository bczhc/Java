package pers.zhc.u.qmcflac_Decode;


import java.util.List;

public class EpicyclesSequence {
    public List<AEpicycle> epicycles;

    public class AEpicycle {
        public int n;
        public ComplexValue c;

        class ComplexValue {
            public double re, im;
        }
    }
}