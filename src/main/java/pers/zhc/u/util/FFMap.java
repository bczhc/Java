package pers.zhc.u.util;

import pers.zhc.u.MathFloatFunctionInterface;

import java.util.ArrayList;
import java.util.List;

public class FFMap {
    private List<FF> ffList = new ArrayList<>();

    public void reset() {
        ffList.clear();
    }

    public class FF {
        private float k, v;

        FF(float k, float v) {
            this.k = k;
            this.v = v;
        }
    }

    public void put(float k, float v) {
        ffList.add(new FF(k, v));
    }

    public float get(float k) {
        for (FF ff : ffList) {
            if (ff.k == k) {
                return ff.v;
            }
        }
        return 0F;
    }

    public MathFloatFunctionInterface getFunction() {
        return v -> {
            for (int i = 1; i < ffList.size(); i++) {
                try {
                    FF ff1 = ffList.get(i - 1);
                    FF ff2 = ffList.get(i + 1);
                    if (v > ff1.k && v <= ff2.k) {
                        return (ff1.v + ff2.v) / 2;
                    }
                } catch (Exception ignored) {
                    return 0;
                }
            }
            return 0;
        };
    }
}
