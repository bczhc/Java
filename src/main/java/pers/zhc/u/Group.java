package pers.zhc.u;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Object> objectList = new ArrayList<>();
    private Object[] objects;
    private boolean havePutObjectArr = false;
    private boolean havePutFile = false, havePutO = false;
    private File f;

    public static void main(String[] args) {
    }

    public Group put(Object o) {
        havePutO = true;
        try {
            if (havePutObjectArr) throw new Exception("\u5df2\u7ecf\u653e\u5165\u5bf9\u8c61\u6570\u7ec4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            objects = (Object[]) o;
            havePutObjectArr = true;
        } catch (ClassCastException ignored) {
            this.objectList.add(o);
        }
        return this;
    }

    public void put(File file) {
        try {
            if (havePutFile) throw new Exception("\u5df2\u7ecf\u8bbe\u7f6e\u4e86\u6587\u4ef6");
            if (havePutO) throw new Exception("\u5df2\u7ecf\u653e\u5165\u4e86\u5bf9\u8c61");
        } catch (Exception e) {
            e.printStackTrace();
        }
        havePutFile = true;
        this.f = file;
    }

    public Object[] group() {
        if (!(havePutO || havePutFile)) try {
            throw new Exception("\u8fd8\u672a\u653e\u5165\u5bf9\u8c61\u6216\u8bbe\u7f6e\u6587\u4ef6");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[0];
    }

    public void group(GroupDo target) {
        if (!(havePutO || havePutFile)) try {
            throw new Exception("\u8fd8\u672a\u653e\u5165\u5bf9\u8c61\u6216\u8bbe\u7f6e\u6587\u4ef6");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (havePutFile) {
        } else if (havePutObjectArr) {
            /*List<List<Object>> listList = new ArrayList<>();
            for (Object o : this.objects) {
                boolean perObjectNotEqual = true;
                if (listList.size() == 0) listList.add(new ArrayList<>());
                for (List<Object> list : listList) {
                    for (Object o1 : list) {
                        if (o1.equals(o)) {
                            list.add(o);
                            perObjectNotEqual = false;
                        }
                    }
                }
                if (perObjectNotEqual) {
                    List<Object> newList = new ArrayList<>();
                    newList.add(o);
                    listList.add(newList);
                }
            }*/
            /*List<Object> duplicateObjectList = new ArrayList<>();
            Object[] os = objectList.toArray(new Object[0]);
            for (int i = 0; i < os.length; i++) { //首先定位到它的一个位置
                List<Object> currentObject_Duplicate = new ArrayList<>(); //在首先的定位了一个对象后创建一个存放与当前定位的对象相同的对象
                List<Integer> theirIndexes = new ArrayList<>(); //同上，这是存放相同对象们的元素位置（相对于成员变量objects的）
                for (int j = 0; j < os.length; j++) { //然后再从头到尾遍历，
                    if (j != i) { //除了刚才定位的位置os[i]，准备看当前的与前面定位的对象是否相同。
                        boolean haveHad_inList = false;
                        for (Object o : duplicateObjectList) {
                            if (os[j].equals(o)) haveHad_inList = true; //但是当前的（二级）也不能在已经找到的重复的对象的集中。
                        }
                        if (!haveHad_inList) {
                            if (os[j].equals(os[i])) { //看当前的对象是否与前面一级定位的对象相同
                                duplicateObjectList.add(os[i]); //如果相同，就添加到前面定义的已经找到的重复的对象的集中，
                                currentObject_Duplicate.add(os[j]);
                                theirIndexes.add(j);
                                //do:
                                target.f(currentObject_Duplicate, theirIndexes);
                            }
                        }
                    }
                }
            }*/
            for (int i = 0; i < objects.length; i++) { //root choice
                List<Object> thisDuplicateList = new ArrayList<>();
                List<Integer> theirIndexes = new ArrayList<>();
                boolean addRootObject = false;
                for (int i1 = 0, objectsLength = objects.length; i1 < objectsLength; i1++) { //expect itself(root choice)
                    Object o = objects[i1];
                    if (i1 != i) {
                        Object rootObject = objects[i];
                        if (o.equals(rootObject)) {
                            if (!addRootObject) {
                                thisDuplicateList.add(rootObject);
                                theirIndexes.add(i);
                            }
                            addRootObject = true;
                            thisDuplicateList.add(o);
                            theirIndexes.add(i1);
                        }
                    }
                }
                target.f(thisDuplicateList, theirIndexes);
            }
        }
    }

    public interface GroupDo {
        void f(List<Object> duplicateObjects, List<Integer> theirIndexes);
    }
}


class s0 {
    public static void main(String[] args) {
        Group g = new Group();
        Integer[] a = {1, 1, 2, 3, 3, 3, 4, 4, 5, 2};
        g.put(a);
        g.group((duplicateObjects, theirIndexes) -> {
            for (Object o : duplicateObjects) {
                System.out.println("o = " + o);
            }
            for (Integer index : theirIndexes) {
                System.out.println("index = " + index);
            }
            System.out.println("---------------------------");
        });
        /*final List[] objectList = new List[]{null};
        final List[] indexes = new List[]{null};
        g.group((duplicateObjects, theirIndexes) -> {
            objectList[0] = duplicateObjects;
            indexes[0] = theirIndexes;
        });
        for (Object o : objectList[0]) {
            System.out.println("o = " + o);
        }
        for (Object index : indexes[0]) {
            System.out.println("index = " + index);
        }*/
        long sum = 0L;
        for (int i = 0; i < 1000000000; i++) {
            sum += (long) i;
            System.out.print(sum + " ");
            if (i % 15 == 0) System.out.print("\n");
        }
        System.out.println("sum = " + sum);
    }
}

class s1 {
    public static void main(String[] args) {
        System.out.println("100");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                int[] ran = Random.ran(10000, 1000000000, 100, false);
                for (int j = 0; j < 1000; j++) {
                    int i1 = Random.ran_sc(0, ran[j % 100]);
                    String factor = new Factor2().factor((long) i1);
                    System.out.println(factor);
                }
            }).start();
        }
    }
}