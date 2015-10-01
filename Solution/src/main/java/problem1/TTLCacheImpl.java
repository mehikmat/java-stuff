package problem1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is Very basic Implementation of {@link problem1.TTLCache}
 * It has number of known limitations. So it is not production ready.
 * Limitations:
 * - Cache is unlimited in size but should have size.
 * - Cache is strict in type but should be generic type
 * - No concurrency handled but should be handled
 * - Cacheable value inner class is also not generic.
 * - TTL has not defalult value and no so flexible and also should have option to
 *   put there forever too.
 * There might be other limitations I have not yet considered because
 * this is just a test you gave me and I have not written test cases for it.
 *
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class TTLCacheImpl implements TTLCache {
    private Map<String, CacheableEntry> cache;

    public TTLCacheImpl() {
        cache = new ConcurrentHashMap<String, CacheableEntry>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String key, Object value, long timeToLiveSeconds) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid Key.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Invalid Value.");
        }
        long expireBy = System.currentTimeMillis() + (timeToLiveSeconds * 1000);
        cache.put(key, new CacheableEntry(expireBy, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid Key.");
        }
        CacheableEntry entry = cache.get(key);

        if (entry == null) {
            return null;
        }
        long timestamp = entry.getExpireBy();
        if (System.currentTimeMillis() > timestamp) {
            cache.remove(key);
            return null;
        }
        return entry.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return cache.size();
    }

    private class CacheableEntry {
        private long expireBy;
        private Object value;

        public CacheableEntry(long expireBy, Object entry) {
            super();
            this.expireBy = expireBy;
            this.value = entry;
        }

        /**
         * @return the expireBy
         */
        public long getExpireBy() {
            return expireBy;
        }

        /**
         * @return the value
         */
        public Object getValue() {
            return value;
        }
    }
}
