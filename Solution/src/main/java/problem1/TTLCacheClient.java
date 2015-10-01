package problem1;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class TTLCacheClient {
    public static void main(String[] args) throws InterruptedException{
        TTLCache cache = new TTLCacheImpl();

        cache.put("key1", "value1", 5);
        cache.put("key2", "value2", 10);

        System.out.println(cache.get("key1")); // should print value1
        System.out.println(cache.get("key2")); // should print value2

        Thread.sleep(1000 * 6);

        System.out.println(cache.get("key1")); // should print NULL
        System.out.println(cache.get("key2")); // should print value2

        Thread.sleep(1000 * 6);

        System.out.println(cache.get("key1")); // should print NULL
        System.out.println(cache.get("key2")); // should print NULL
    }
}
