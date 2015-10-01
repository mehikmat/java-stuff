package problem1;

/**
 * TTLCache is a special type of cache where users can put objects in the cache with time to live
 * The objects should automatically expire after their time to live. The user should be able to define different *
 * time to live for different items placed in the Cache.
 *
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public interface TTLCache {
    /**
     * @param key ­ The key to associate with the cache
     * @param value ­ The actual value to store in the cache
     * @param timeToLiveSeconds ­ The time to live for this object in the cache
     */
    public void put(String key, Object value, long timeToLiveSeconds);

    /**
     * Returns the Object in the cache that is associated with passed key, or NULL if
     * no value is associated with the key
     * @param key ­ The key associated with the value to retrieve
     *
     */
    public Object get(String key);

    /**
     * @return ­ The number of objects in the cache
     */
    public int size();

}
