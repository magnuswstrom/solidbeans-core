package com.solidbeans.core.async;

import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Async executor runs runnable and callable in it's own thread pool
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AsyncExecutor {

    private static final Logger log = LoggerFactory.getLogger(AsyncExecutor.class);

    private final ListeningExecutorService executorSerive;

    /**
     * Creates async executor which will hold a underlying thread pool to execute it's tasks
     *
     * @param poolSize Underlying thread pool size, it will be a fixed thread pool
     * @param threadPriority Thread priority that tasks will be executed in
     */
    public AsyncExecutor(int poolSize, int threadPriority) {
        checkArgument(poolSize > 0, "Invalid pool size");
        checkArgument(threadPriority >= Thread.MIN_PRIORITY, "Invalid thread priority");
        checkArgument(threadPriority <= Thread.MAX_PRIORITY, "Invalid thread priority");

        this.executorSerive = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(poolSize, new AsyncThreadFactory(threadPriority)));
    }

    /**
     * Adds a runnable to be executed in the future
     *
     * @param runnable Runnable to execute in the future
     */
    public void execute(Runnable runnable) {
        checkNotNull(runnable, "Runnable is null");
        log.info(Thread.currentThread().getName() + " adds runnable to queue");

        executorSerive.execute(new AsyncRunnable(runnable));
    }

    /**
     * Adds callable to be executed in the future
     *
     * @param callable Callable to execute in the future
     * @param listener Callable listener to be notified when callable is finished
     * @param <T> Type of callable result
     */
    public <T> void submit(Callable<T> callable, FutureCallback<T> listener) {
        checkNotNull(callable, "Callable is null");
        log.info(Thread.currentThread().getName() + " adds callable to queue");

        ListenableFuture<T> future = executorSerive.submit(new AsyncCallable<>(callable));

        if(Objects.nonNull(listener)) {
            Futures.addCallback(future, listener);
        }
    }

    /**
     * Callback class that logs callable execution
     *
     * @param <T> Type of callable result
     */
    private class AsyncCallable<T> implements Callable<T> {

        private final Callable<T> callable;

        private AsyncCallable(Callable<T> callable) {
            this.callable = callable;
        }

        @Override
        public T call() throws Exception {
            log.info(Thread.currentThread().getName() + " executes callable");
            long time = System.currentTimeMillis();

            T result = null;

            if(Objects.nonNull(callable)) {
                result = callable.call();
            }

            log.info(Thread.currentThread().getName() + " finished callable in " + (System.currentTimeMillis() - time) + " ms");

            return result;
        }
    }

    /**
     * Runnable class that logs runnable execution
     */
    private class AsyncRunnable implements Runnable {

        private final Runnable runnable;

        private AsyncRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            log.info(Thread.currentThread().getName() + " executes runnable");
            long time = System.currentTimeMillis();

            if(Objects.nonNull(runnable)) {
                runnable.run();
            }

            log.info(Thread.currentThread().getName() + " finished runnable in " + (System.currentTimeMillis() - time) + " ms");
        }
    }

    /**
     * Thread factory that creates named threads with specified priority
     */
    private class AsyncThreadFactory implements ThreadFactory {

        private final int threadPriority;
        private int threadCount;

        private AsyncThreadFactory(int threadPriority) {
            this.threadPriority = threadPriority;
            this.threadCount = 0;
        }

        @Override
        public Thread newThread(Runnable runnable) {
            String threadName = AsyncExecutor.class.getSimpleName() + "Thread-" + (++threadCount);

            log.info(Thread.currentThread().getName() + " creates service thread " + threadName + " with priority " + threadPriority);

            Thread thread = new Thread(runnable, threadName);
            thread.setPriority(threadPriority);

            return thread;
        }
    }
}
