package com.solidbeans.core.async;

import com.google.common.util.concurrent.FutureCallback;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public class AsyncExecutorTest {

    @Test
    public void testAsyncSender() {
        AsyncExecutor sender = new AsyncExecutor(3, 4);
        AsyncCallbackCounter counter = new AsyncCallbackCounter();
        AsyncCallback callback = new AsyncCallback(counter);

        sender.submit(this::call, callback);
        sender.submit(this::call, callback);
        sender.submit(this::call, callback);
        sender.submit(this::call, callback);
        sender.submit(this::call, callback);
        sender.submit(this::call, callback);

        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);
        sender.execute(counter::increment);

        suspend(1000);

        Assert.assertThat(counter.count(), is(14));
    }

    private String call() {
        return null;
    }

    private void suspend(long milliseconds) {
        try {
            Thread.currentThread().sleep(milliseconds);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class AsyncCallbackCounter {

        private int counter = 0;

        synchronized void increment() {
            counter++;
        }

        synchronized int count() {
            return counter;
        }
    }

    private class AsyncCallback implements FutureCallback<String> {

        private final AsyncCallbackCounter counter;

        private AsyncCallback(AsyncCallbackCounter counter) {
            this.counter = counter;
        }

        @Override
        public void onSuccess(String result) {
            counter.increment();
        }

        @Override
        public void onFailure(Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
