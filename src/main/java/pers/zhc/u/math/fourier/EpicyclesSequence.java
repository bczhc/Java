package pers.zhc.u.math.fourier;


import pers.zhc.u.math.util.ComplexValue;

import java.util.ArrayList;
import java.util.List;

public class EpicyclesSequence {
    public List<AEpicycle> epicycles;

    public EpicyclesSequence() {
        this.epicycles = new ArrayList<>();
    }

    public static class AEpicycle {

        public AEpicycle(double n, ComplexValue c) {
            this.n = n;
            this.c = c;
        }

        public double n;
        public ComplexValue c;
    }

    public void put(double n, ComplexValue c) {
        this.epicycles.add(new AEpicycle(n, c));
    }

    public void put(double n, double complexReP, double complexImP) {
        this.epicycles.add(new AEpicycle(n, new ComplexValue(complexReP, complexImP)));
    }

    public void put(AEpicycle aEpicycle) {
        this.epicycles.add(aEpicycle);
    }
}