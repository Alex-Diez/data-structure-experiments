package ua.ds;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

//    Intel(R) Core(TM) i5-5257U CPU @ 2.70 GHz, 2 Core(s), 4 Logical Processor(s)
//          cache sizes                 queue sizes
//    L1D   32KB     32768      4096      8192     16384
//    L2   256KB    262144     32768     65536    131072
//    L3     3MB   3145728    524288   1048576   2097152
//
//    Intel(R) Core(TM) i7-3770 CPU @ 3.40GHz, 4 Core(s), 8 Logical Processor(s)
//          cache sizes                 queue sizes
//    L1D   32KB       32768     4096      8192     16384
//    L2   256KB      262144    32768     65536    131072
//    L3     8MB     8388608  1048576   2097152   4194304
@State(Scope.Benchmark)
public class ConstantCapacityArrayQueueBenchmark {

  @Param({"512", "1024", "2048", "4096", "8192", "16384", "32768", "65536", "131072", "262144", "524288", "1048576"})
  private int iterations;

  @Benchmark
  public NonResizableArrayQueuePrimitive enqueue_primitive() {
    NonResizableArrayQueuePrimitive queue = new NonResizableArrayQueuePrimitive(iterations);
    for (int i = 0; i < iterations; i++) {
      queue.enqueue(i);
    }
    return queue;
  }

  @Benchmark
  public int deque_primitive() {
    NonResizableArrayQueuePrimitive queue = enqueue_primitive();
    int sum = 0;
    for (int i = 0; i < iterations; i++) {
      sum += queue.deque();
    }
    return sum;
  }

  @Benchmark
  public NonResizableArrayQueueBoxed enqueue_boxed() {
    NonResizableArrayQueueBoxed queue = new NonResizableArrayQueueBoxed(iterations);
    for (int i = 0; i < iterations; i++) {
      queue.enqueue(i);
    }
    return queue;
  }

  @Benchmark
  public int deque_boxed() {
    NonResizableArrayQueueBoxed queue = enqueue_boxed();
    int sum = 0;
    for (int i = 0; i < iterations; i++) {
      sum += queue.deque();
    }
    return sum;
  }
}
