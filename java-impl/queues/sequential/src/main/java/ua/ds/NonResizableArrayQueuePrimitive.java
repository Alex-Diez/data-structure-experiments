package ua.ds;

import org.openjdk.jol.info.ClassLayout;

public class NonResizableArrayQueuePrimitive implements SequentialQueue {
  private final int[] items;
  private int head;
  private int tail;
  private int size;

  public NonResizableArrayQueuePrimitive() {
    this(16);
  }

  public NonResizableArrayQueuePrimitive(int capacity) {
    capacity = SequentialQueue.nextPowerOfTwo(capacity);
    items = new int[capacity];
    size = 0;
    head = 0;
    tail = 0;
  }

  @Override
  public void enqueue(int item) {
    items[tail++] = item;
    if (tail == items.length) tail = 0;
    size++;
  }

  @Override
  public int deque() {
    if (isEmpty()) return -1;
    int item = items[head];
    size--;
    head++;
    if (head == items.length) head = 0;
    return item;
  }

  private boolean isEmpty() {
    return size == 0;
  }

  public static void main(String[] args) {
    System.out
        .println(ClassLayout.parseInstance(new NonResizableArrayQueuePrimitive()).toPrintable());
    System.out.println(ClassLayout.parseClass(NonResizableArrayQueuePrimitive.class).toPrintable());
  }
}
