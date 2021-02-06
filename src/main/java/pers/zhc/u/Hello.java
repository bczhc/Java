package pers.zhc.u;

import java.util.Iterator;
import java.util.LinkedList;

public class Hello {
    class A implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Integer next() {
            return null;
        }
    }
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        iterator.hasNext();
    }
}