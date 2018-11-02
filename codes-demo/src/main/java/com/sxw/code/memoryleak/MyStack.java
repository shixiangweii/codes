package com.sxw.code.memoryleak;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-11-02
 * Time: 11:56
 *
 * @author shixiangweii
 */
public class MyStack<T> {
    private T[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 16;

    public MyStack() {
        elements = (T[]) new Object[INIT_CAPACITY];
    }

    public void push(T elem) {
        ensureCapacity();
        elements[size++] = elem;
    }

    /**
     * 其实内存“泄露”，这个“泄露”本来就是一个比喻，难道内存真的会像水一样流出来？！
     * 个人理解就是，其实没什么卵用的内存了，但是因为编码的原因，明明没有用可以被回收的内存
     * 却因为被无意的保留下来，结果，无法GC，
     * 最后这样的无法GC的多了，造成OOM
     * @return
     */
    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        /*
        pop方法弹出栈中的对象时，该对象不会被当作垃圾回收，
        即使使用栈的程序不再引用这些对象，因为栈内部维护着对这些对象的过期引用（obsolete reference）

        “内存泄露”其实就是无意识的对象保持

        垃圾回收器不会处理这个对象，也不会处理该对象引用的其他对象，
        即使这样的对象只有少数几个，也可能会导致很多的对象被排除在垃圾回收之外，从而对性能造成重大影响

        极端情况下会引发Disk Paging（物理内存与硬盘的虚拟内存交换数据），甚至造成OutOfMemoryError
        最后内存泄露，会造成OOM
         */
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
