package ua.ds.array.primitive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ConditionalNonResizableArrayQueuePrimitiveWithIncrementTest {

  private ConditionalNonResizableArrayQueuePrimitiveWithIncrement queue;

  @BeforeEach
  void setUp() throws Exception {
    queue = new ConditionalNonResizableArrayQueuePrimitiveWithIncrement();
  }

  @Test
  void dequeFromEmptyQueue() throws Exception {
    assertThat(queue.deque(), is(-1));
  }

  @Test
  void enqueueDequeItem() throws Exception {
    queue.enqueue(10);

    assertThat(queue.deque(), is(10));
  }

  @Test
  void enqueueDequeItems() throws Exception {
    queue.enqueue(10);

    assertThat(queue.deque(), is(10));

    queue.enqueue(20);

    assertThat(queue.deque(), is(20));

    queue.enqueue(30);

    assertThat(queue.deque(), is(30));
  }

  @Test
  void enqueueManyItemsDequeManyItems() throws Exception {
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);

    assertThat(queue.deque(), is(10));
    assertThat(queue.deque(), is(20));
    assertThat(queue.deque(), is(30));
  }

  @Test
  void enqueueDequeMoreThanCapacity() throws Exception {
    for (int i = 0; i < 32; i++) {
      queue.enqueue(i);
      queue.deque();
    }
  }
}
