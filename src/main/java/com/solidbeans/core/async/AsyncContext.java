package com.solidbeans.core.async;

import com.google.common.collect.Maps;
import com.solidbeans.core.util.UUIDGenerator;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public class AsyncContext {

    public interface AsyncProperties {
        String AsyncId = "async.properties.id";
    }

    private final Map<String, Object> context;

    private AsyncContext(Map<String, Object> context) {
        checkNotNull(context, "Context is null");

        this.context = context;
        this.context.put(AsyncProperties.AsyncId, UUIDGenerator.uuid());
    }

    public static AsyncContext newContext() {
        return new AsyncContext(Maps.newHashMap());
    }

    public static AsyncContext newContext(Map<String, Object> originalMap) {
        checkNotNull(originalMap, "Original map is null");

        return new AsyncContext(Maps.newHashMap(originalMap));
    }

    public Object get(String key) {
        return context.get(key);
    }

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
