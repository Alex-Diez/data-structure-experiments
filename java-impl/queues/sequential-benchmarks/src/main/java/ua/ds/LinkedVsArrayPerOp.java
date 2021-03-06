package ua.ds;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import ua.ds.array.primitive.ConditionalNonResizableArrayQueuePrimitive;
import ua.ds.linked.primitive.LinkedQueuePrimitive;

@Fork(3)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public abstract class LinkedVsArrayPerOp extends QueueMethods {
  @Setup(Level.Invocation)
  public void populateData() {
    int size = size();
    Random r = new Random();
    data = new int[size];
    for (int i = 0; i < data.length; i++) {
      data[i] = r.nextInt();
    }
  }

  abstract int size();

  public static abstract class GeneralSetup extends LinkedVsArrayPerOp {

    LinkedQueuePrimitive linked;
    LinkedQueuePrimitive linked_parallel;
    ConditionalNonResizableArrayQueuePrimitive array;

    @Setup
    public void setUp() {
      linked = new LinkedQueuePrimitive();
      linked_parallel = new LinkedQueuePrimitive();
      array = new ConditionalNonResizableArrayQueuePrimitive(size());
    }
  }

  public static class Small extends GeneralSetup {
    static final int SIZE = K;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Medium extends GeneralSetup {
    static final int SIZE = 32 * K;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large extends GeneralSetup {
    static final int SIZE = M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large2 extends GeneralSetup {
    static final int SIZE = 2 * M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large4 extends GeneralSetup {
    static final int SIZE = 4 * M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large8 extends GeneralSetup {
    static final int SIZE = 8 * M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large16 extends GeneralSetup {
    static final int SIZE = 16 * M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }

  public static class Large32 extends GeneralSetup {
    static final int SIZE = 32 * M;

    @Override
    int size() {
      return SIZE;
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int linked() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseParallelGC")
    @OperationsPerInvocation(SIZE)
    public int linked_parallel() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @Fork(value = 3, jvmArgs = "-XX:+UseConcMarkSweepGC")
    @OperationsPerInvocation(SIZE)
    public int linked_cms() {
      return dequeMany(enqueueMany(linked));
    }

    @Benchmark
    @OperationsPerInvocation(SIZE)
    public int array() {
      return dequeMany(enqueueMany(array));
    }
  }
}
