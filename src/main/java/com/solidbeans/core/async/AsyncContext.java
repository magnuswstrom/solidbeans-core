package com.solidbeans.core.async;

import com.google.common.collect.Maps;
import com.solidbeans.core.util.SolidUtil;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public class AsyncContext {

    /**
     * Common async properties
     */
    public interface AsyncProperties {
        /**
         * Async id to use for i.e logging
         */
        String AsyncId = "async.properties.id";
    }

    private final Map<String, Object> context;

    /**
     * Creates a async context with map as starting context
     *
     * @param context Initial context
     */
    private AsyncContext(Map<String, Object> context) {
        checkNotNull(context, "Context is null");

        this.context = context;
        this.context.put(AsyncProperties.AsyncId, SolidUtil.Uuid.uuid());
    }

    /**
     * Creates a new async context
     *
     * @return Async context
     */
    public static AsyncContext newContext() {
        return new AsyncContext(Maps.newHashMap());
    }

    /**
     * Creates a async context with map as starting context
     *
     * @param initialContext Initial context
     * @return Async context
     */
    public static AsyncContext newContext(Map<String, Object> initialContext) {
        checkNotNull(initialContext, "Initial context is null");

        return new AsyncContext(Maps.newHashMap(initialContext));
    }

    /**
     * Get value for key from context
     *
     * @param key Key to get value for
     * @return Value for key
     */
    public Object get(String key) {
        return context.get(key);
    }

    /**
     * Put key and value into context
     *
     * @param key Key to put into context
     * @param value Value to put into context
     */
    public void put(String key, Object value) {
        context.put(key, value);
    }

    @Override
    public String toString() {
        return "AsyncContext{" +
                "context=" + context +
                '}';
    }
}
