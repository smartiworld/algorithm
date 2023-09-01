package com.iworld.algorithm.array;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gq.cai
 * @version 1.0
 * @description 堆 父子位置 父n 左孩子2n+1 右孩子2n+2  子n  父（n-1）/2
 * @date 2021/7/27 13:31
 */
public class EnhanceHeap<E> {
    
    Object[] element;
    //记录当前放入元素在数组的位置 有重复对象 取出当前位置重新构建堆
    Map<E, Integer> indexMap;
    
    int capacity;
    
    int defaultCapacity = 10;
    
    int heapSize = 0;
    //排序 比较器 自定义排序方式
    // 任何比较器：
    // compare方法里，遵循一个统一的规范：
    // 返回负数的时候，认为第一个参数应该排在前面
    // 返回正数的时候，认为第二个参数应该排在前面
    // 返回0的时候，认为无所谓谁放前面
    Comparator<E> comparator;
    
    public EnhanceHeap() {
        capacity = defaultCapacity;
        element = new Object[defaultCapacity];
        indexMap = new HashMap<>(defaultCapacity);
    }
    
    public EnhanceHeap(Comparator<E> comparator) {
        capacity = defaultCapacity;
        element = new Object[defaultCapacity];
        indexMap = new HashMap<>(defaultCapacity);
        this.comparator = comparator;
    }
    
    public EnhanceHeap(int size) {
        capacity = size;
        element = new Object[size];
        indexMap = new HashMap<>(size);
    }
    
    public EnhanceHeap(int size, Comparator<E> comparator) {
        capacity = size;
        element = new Object[size];
        indexMap = new HashMap<>(size);
        this.comparator = comparator;
    }
    
    /**
     * 压入一个元素 先放入数组最后一个位置 然后和父节点对比交换 直到不大于父节点（大根堆）或者不小于父节点（小根堆）
     * 如果元素已经在堆中存放过一次 则调用rePush 重新计算当前元素在堆上的位置
     * @param e
     */
    public void push(E e) {
        // 记录放入元素得位置 如果存放过则需要将当前元素调整位置
        Integer index = indexMap.get(e);
        if (index != null) {
            rePush(e);
            return ;
        }
        if (capacity == heapSize) {
            throw new RuntimeException("堆满了");
        }
        if (e == null) {
            throw new NullPointerException();
        }
        // 元素放最后一个位置
        element[heapSize] = e;
        indexMap.put(e, heapSize);
        if (comparator != null) {
            upwardWithComparator(element, heapSize++);
        } else {
            upward(element, heapSize++);
        }
    }
    
    /**
     * 修改对象后的值 重新构建堆
     * @param e
     */
    public void rePush(E e) {
        Integer index = indexMap.get(e);
        if (index == null) {
            if (capacity == heapSize) {
                throw new RuntimeException("堆满了");
            }
            if (e == null) {
                throw new NullPointerException();
            }
            push(e);
            return ;
        }
        if (comparator != null) {
            upwardWithComparator(element, index);
            underWithComparator(element, index, heapSize);
        } else {
            upward(element, index);
            under(element, index, heapSize);
        }
    }
    
    /**
     * 上浮 和父节点比较
     * @param objs
     * @param index
     */
    public void upward(Object[] objs, int index) {
        int parentIndex = (index - 1) / 2;
        while (((Comparable)objs[parentIndex]).compareTo((E) objs[index]) > 0) {
            swap(element, parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    
    /**
     *
     * @param objs
     * @param index
     */
    public void upwardWithComparator(Object[] objs, int index) {
        int parentIndex = (index - 1) / 2;
        while (comparator.compare((E) objs[parentIndex], (E) objs[index]) > 0) {
            swap(element, parentIndex, index);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }
    
    public E pop() {
        E result = (E)element[0];
        indexMap.remove(result);
        swap(element, 0, --heapSize);
        if (comparator != null) {
            underWithComparator(element, 0, heapSize);
        } else {
            under(element, 0, heapSize);
        }
        return result;
    }
    
    public void underWithComparator(Object[] objs, int index, int heapSize) {
        int left = (index << 1) | 1;
        while (left < heapSize) {
            int less = (left + 1) < heapSize && comparator.compare((E)objs[left], (E)objs[left + 1]) > 0 ? left + 1 : left;
            less = comparator.compare((E)objs[index], (E)objs[less]) > 0 ? less : index;
            if (less == index) {
                break;
            }
            swap(element, less, index);
            index = less;
            left = (index << 1) | 1;
        }
    }
    
    public void under(Object[] objs, int index, int heapSize) {
        int left = (index << 1) | 1;
        while (left < heapSize) {
            int less = (left + 1) < heapSize && ((Comparable)objs[left]).compareTo((E)objs[left + 1]) > 0 ? left + 1 : left;
            less = ((Comparable)objs[index]).compareTo((E)objs[less]) > 0 ? less : index;
            if (less == index) {
                break;
            }
            swap(element, less, index);
            index = less;
            left = (index << 1) | 1;
        }
    }
    
    public E get() {
        return (E) element[0];
    }
    
    private void swap(Object[] objs, int l, int r) {
        Object tmp = objs[l];
        objs[l] = objs[r];
        indexMap.put((E) objs[r], l);
        objs[r] = tmp;
        indexMap.put((E) tmp, r);
    }
    
    public boolean isEmpty() {
        return heapSize == 0;
    }
    
    public static void main(String[] args) {
        EnhanceHeap<Integer> enhanceHeap = new EnhanceHeap<>();
        Type genericSuperclass = enhanceHeap.getClass().getGenericSuperclass();
        Class<?> superclass = enhanceHeap.getClass().getSuperclass();
        System.out.println(genericSuperclass);
        System.out.println(superclass);
        enhanceHeap.push(11);
        enhanceHeap.push(15);
        enhanceHeap.push(1);
//        enhanceHeap.push(18);
//        enhanceHeap.push(19);
//        enhanceHeap.push(25);
//        enhanceHeap.push(3);
//        enhanceHeap.push(9);
//        enhanceHeap.push(3);
//        enhanceHeap.push(22);
        while (!enhanceHeap.isEmpty()) {
            System.out.print(enhanceHeap.pop() + ",");
        }
        EnhanceHeap<User> enhanceHeapUser = new EnhanceHeap<>((o1, o2) -> (o2.age - o1.age != 0) ? o2.age - o1.age : o2.id - o1.id);
        User user1 = new User(1,12, "d");
        User user2 = new User(2,10, "c");
        User user3 = new User(5,12, "d");
        User user4 = new User(12,18, "q");
        User user5 = new User(22,20, "f");
        User user6 = new User(8,15, "a");
        User user7 = new User(6,8, "l");
        User user8 = new User(3,17, "k");
        User user9 = new User(16,9, "j");
        User user10 = new User(10,19, "g");
        enhanceHeapUser.push(user1);
        enhanceHeapUser.push(user2);
        enhanceHeapUser.push(user3);
        enhanceHeapUser.push(user4);
        enhanceHeapUser.push(user5);
        enhanceHeapUser.push(user6);
        enhanceHeapUser.push(user7);
        enhanceHeapUser.push(user8);
        enhanceHeapUser.push(user9);
        enhanceHeapUser.push(user10);
        
        
//        while (!enhanceHeapUser.isEmpty()) {
//            System.out.println(enhanceHeapUser.pop());
//        }
        User user = enhanceHeapUser.get();
        System.out.println(user);
        user.age = 22;
        user.id = 4;
        enhanceHeapUser.rePush(user);
        System.out.println("-----");
        while (!enhanceHeapUser.isEmpty()) {
            System.out.println(enhanceHeapUser.pop());
        }
    }
    
    public static class User{
        private int id;
        private int age;
        private String name;
        
        public User(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }
    
        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", id=" + id +
                    ", name=" + name +
                    '}';
        }
    }
}
