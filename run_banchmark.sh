#!/usr/bin/env bash

mkdir -p benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge \
&& cd rust-impl \
&& RUST_TEST_THREADS=1 cargo bench benchmarks::rc_ref_cell_linked_queue | tee ../benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge/rc_ref_cell_linked_queue \
&& RUST_TEST_THREADS=1 cargo bench benchmarks::shared_linked_queue | tee ../benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge/shared_linked_queue \
&& RUST_TEST_THREADS=1 cargo bench benchmarks::non_resizable_array_queue | tee ../benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge/non_resizable_array_queue \
&& RUST_TEST_THREADS=1 cargo bench benchmarks::rc_ref_cell_linked_array_queue | tee ../benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge/rc_ref_cell_linked_array_queue \
&& RUST_TEST_THREADS=1 cargo bench benchmarks::shared_linked_array_queue | tee ../benchmarks-result/Intel-i7-3770-3.40GHz-Ivy-Bridge/shared_linked_array_queue \
&& cd .. \
&& cd java-impl \
&& gradle clean jmhJar \
&& cd queues/sequential \
&& mkdir -p build/reports/jmh/ \
&& java -jar build/libs/sequential-1.0-jmh.jar -e 'ConditionVsBitMask|LinkedArrays|PrimitiveVsBoxed' -f 1 -wi 10 -i 10 -gc true -tu ns -bm avgt -rf JSON -rff build/reports/jmh/array-vs-linked-results.json \
&& java -jar build/libs/sequential-1.0-jmh.jar -e 'ConditionVsBitMask|LinkedArrays|LinkedVsArrayVsDirectVsHeap' -f 1 -wi 10 -i 10 -tu ns -bm thrpt -rf JSON -rff build/reports/jmh/primitive-vs-boxed-results.json \
&& java -jar build/libs/sequential-1.0-jmh.jar -e 'LinkedArrays|LinkedVsArrayVsDirectVsHeap|PrimitiveVsBoxed' -f 1 -wi 10 -i 10 -tu ns -bm thrpt -rf JSON -rff build/reports/jmh/mask-vs-condition-results.json \
&& java -jar build/libs/sequential-1.0-jmh.jar -e 'ConditionVsBitMask|PrimitiveVsBoxed|LinkedVsArrayVsDirectVsHeap' -f 1 -wi 10 -i 10 -tu ns -bm thrpt -rf JSON -rff build/reports/jmh/linked-arrays-results.json \
&& cd ../.. \
&& cd queues/blocking \
&& mkdir -p build/reports/jmh/ \
&& java -jar build/libs/blocking-1.0-jmh.jar -e 'DoubleLockLinkedBlockingQueuePaddedBenchmarks|SingleLockLinkedBlockingQueuePaddedBenchmarks' -f 1 -i 10 -wi 10  -gc true -tu s -bm thrpt -rf JSON -rff build/reports/jmh/lock-thrpt-results.json \
&& java -jar build/libs/blocking-1.0-jmh.jar -e 'ArrayBlockingQueueBenchmarks' -f 1 -i 10 -wi 10  -gc true -tu s -bm thrpt -rf JSON -rff build/reports/jmh/lock-padded-results.json