package ua.ds.array.primitive;

import ua.ds.array.ArrayQueue;
import ua.ds.SequentialQueue;

public class ConditionalNonResizableArrayQueuePrimitive implements SequentialQueue {
  private int[] items;
  private int head;
  private int tail;
  private int size;

  public ConditionalNonResizableArrayQueuePrimitive() {
    this(16);
  }

  public ConditionalNonResizableArrayQueuePrimitive(int capacity) {
    capacity = ArrayQueue.nextPowerOfTwo(capacity);
    items = new int[capacity];
    head = 0;
    tail = 0;
    size = 0;
  }

  @Override
  public void enqueue(int item) {
    items[tail] = item;
    if (tail + 1 == items.length) tail = 0;
    else tail += 1;
    size++;
  }

  @Override
  public int deque() {
    if (isEmpty()) return -1;
    int item = items[head];
    size--;
    if (head + 1 == items.length) head = 0;
    else head += 1;
    return item;
  }

  private boolean isEmpty() {
    return size == 0;
  }
}
