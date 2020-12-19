package pers.zhc.u.util;

import java.util.ArrayList;
import java.util.List;

public class LongMap<T> {

    private static class Bean<T> {
        long key;
        T value;

        public Bean(long key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Bean<T>> list = new ArrayList<>();

    public void add(long key, T o) {
        list.add(new Bean<>(key, o));
    }

    public T get(long key) {
        for (Bean<T> bean : list) {
            if (bean.key == key) {
                return bean.value;
            }
        }
        return null;
    }
}
