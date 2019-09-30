package pers.zhc.u.math.fourier;


import pers.zhc.u.math.util.ComplexValue;

import java.util.ArrayList;
import java.util.List;

public class EpicyclesSequence {
    public List<AEpicycle> epicycles;
    private int index_n_0 = -1;

    public EpicyclesSequence() {
        this.epicycles = new ArrayList<>();
    }

    public static class AEpicycle {

        public AEpicycle(int n, ComplexValue c) {
            this.n = n;
            this.c = c;
        }

        public int n;
        public ComplexValue c;
    }

    public void put(int n, ComplexValue c) {
        if (n == 0) this.index_n_0 = this.epicycles.size();
        this.epicycles.add(new AEpicycle(n, c));
    }

    public void put(int n, double complexReP, double complexImP) {
        if (n == 0) this.index_n_0 = this.epicycles.size();
        this.epicycles.add(new AEpicycle(n, new ComplexValue(complexReP, complexImP)));
    }

    public void put(AEpicycle aEpicycle) {
        if (aEpicycle.n == 0) this.index_n_0 = this.epicycles.size();
        this.epicycles.add(aEpicycle);
    }

    public int get_n_0_index() {
        return this.index_n_0;
    }
}