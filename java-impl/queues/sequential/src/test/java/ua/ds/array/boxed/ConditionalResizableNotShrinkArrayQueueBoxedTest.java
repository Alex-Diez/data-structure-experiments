package ua.ds.array.boxed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.ds.array.boxed.ConditionalResizableNotShrinkArrayQueueBoxed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class ConditionalResizableNotShrinkArrayQueueBoxedTest {

  private ConditionalResizableNotShrinkArrayQueueBoxed queue;

  @BeforeEach
  void setUp() throws Exception {
    queue = new ConditionalResizableNotShrinkArrayQueueBoxed();
  }

  @Test
  void dequeFromEmptyQueue() throws Exception {
    assertThat(queue.deque(), is(nullValue()));
  }

  @Test
  void enqueueDequeItem() throws Exception {
    queue.enqueue(10);

    assertThat(queue.deque(), is(10));
  }

  @Test
  void enqueueDequeManyItems() throws Exception {
    queue.enqueue(10);

    assertThat(queue.deque(), is(10));

    queue.enqueue(20);

    assertThat(queue.deque(), is(20));

    queue.enqueue(30);

    assertThat(queue.deque(), is(30));
  }

  @Test
  void enqueueManyItems_dequeManyItems() throws Exception {
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);

    assertThat(queue.deque(), is(10));
    assertThat(queue.deque(), is(20));
    assertThat(queue.deque(), is(30));
  }

  @Test
  void enqueueMoreThanCapacity() throws Exception {
    for (int i = 0; i < 20; i++) {
      queue.enqueue(i);
    }

    for (int i = 0; i < 20; i++) {
      assertThat(queue.deque(), is(i));
    }
  }

  @Test
  void enqueueToDoubleResize() throws Exception {
    for (int i = 0; i < 100; i++) {
      queue.enqueue(i);
    }

    for (int i = 0; i < 100; i++) {
      assertThat(queue.deque(), is(i));
    }
  }

  @Test
  void enqueueDequeManyTimeMoreThanCapacity() throws Exception {
    for (int i = 0; i < 40; i++) {
      queue.enqueue(i);
    }

    for (int i = 0; i < 20; i++) {
      assertThat(queue.deque(), is(i));
    }

    for (int i = 40; i < 80; i++) {
      queue.enqueue(i);
    }

    for (int i = 20; i < 40; i++) {
      assertThat(queue.deque(), is(i));
    }

    for (int i = 80; i < 120; i++) {
      queue.enqueue(i);
    }

    for (int i = 40; i < 60; i++) {
      assertThat(queue.deque(), is(i));
    }
  }
}
