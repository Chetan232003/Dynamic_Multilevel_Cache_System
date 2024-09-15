import java.util.*;

class CacheLevel {
    private int size;
    private String evictionPolicy;
    private Map<String, String> cache;
    private LinkedList<String> accessOrder;  // For LRU

    public CacheLevel(int size, String evictionPolicy) {
        this.size = size;
        this.evictionPolicy = evictionPolicy;
        this.cache = new HashMap<>();
        this.accessOrder = new LinkedList<>();
    }

    public String get(String key) {
        if (cache.containsKey(key)) {
            if (evictionPolicy.equals("LRU")) {
                accessOrder.remove(key);
                accessOrder.addLast(key);
            }
            return cache.get(key);
        }
        return null;
    }

    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            if (evictionPolicy.equals("LRU")) {
                accessOrder.remove(key);
            }
        } else if (cache.size() >= size) {
            evict();
        }

        cache.put(key, value);
        if (evictionPolicy.equals("LRU")) {
            accessOrder.addLast(key);
        }
    }

    private void evict() {
        if (evictionPolicy.equals("LRU")) {
            String lruKey = accessOrder.removeFirst();  // Evict the least recently used item
            cache.remove(lruKey);
        } else if (evictionPolicy.equals("LFU")) {
            String lfuKey = cache.keySet().stream()
                    .min(Comparator.comparingInt(key -> Collections.frequency(accessOrder, key)))
                    .orElse(null);  // Find the least frequently used item
            if (lfuKey != null) {
                cache.remove(lfuKey);
                accessOrder.remove(lfuKey);
            }
        }
    }

    public void display() {
        System.out.println(cache);
    }
}

class MultilevelCache {
    private List<CacheLevel> cacheLevels;

    public MultilevelCache() {
        this.cacheLevels = new ArrayList<>();
    }

    public void addCacheLevel(int size, String evictionPolicy) {
        cacheLevels.add(new CacheLevel(size, evictionPolicy));
    }

    public void removeCacheLevel(int level) {
        if (level >= 0 && level < cacheLevels.size()) {
            cacheLevels.remove(level);
            System.out.println("Cache level " + (level + 1) + " removed.");
        } else {
            System.out.println("Invalid level number.");
        }
    }

    public String get(String key) {
        for (CacheLevel cache : cacheLevels) {
            String value = cache.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public void put(String key, String value) {
        if (!cacheLevels.isEmpty()) {
            cacheLevels.get(0).put(key, value);
        }
    }

    public void displayCache() {
        for (int i = 0; i < cacheLevels.size(); i++) {
            System.out.println("Cache Level L" + (i + 1) + ":");
            cacheLevels.get(i).display();
        }
    }
}

public class DynamicMultilevelCacheSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MultilevelCache cacheSystem = new MultilevelCache();

        while (true) {
            System.out.println("\n1. Add Cache Level");
            System.out.println("2. Insert Key-Value Pair");
            System.out.println("3. Get Value by Key");
            System.out.println("4. Remove Cache Level");
            System.out.println("5. Display Cache");
            System.out.println("6. Exit");
            
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter cache size: ");
                    int size = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter eviction policy (LRU or LFU): ");
                    String evictionPolicy = scanner.nextLine().toUpperCase();

                    if (evictionPolicy.equals("LRU") || evictionPolicy.equals("LFU")) {
                        cacheSystem.addCacheLevel(size, evictionPolicy);
                        System.out.println("Added cache level with size " + size + " and policy " + evictionPolicy);
                    } else {
                        System.out.println("Invalid eviction policy. Please enter 'LRU' or 'LFU'.");
                    }
                    break;

                case "2":
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();

                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();

                    cacheSystem.put(key, value);
                    System.out.println("Inserted key-value pair (" + key + ", " + value + ") into L1.");
                    break;

                case "3":
                    System.out.print("Enter key to retrieve: ");
                    key = scanner.nextLine();
                    String result = cacheSystem.get(key);

                    if (result != null) {
                        System.out.println("Value found: " + result);
                    } else {
                        System.out.println("Cache miss! Value not found.");
                    }
                    break;

                case "4":
                    System.out.print("Enter cache level to remove (1 for L1, 2 for L2, ...): ");
                    int level = Integer.parseInt(scanner.nextLine()) - 1;
                    cacheSystem.removeCacheLevel(level);
                    break;

                case "5":
                    cacheSystem.displayCache();
                    break;

                case "6":
                    System.out.println("Exiting.");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
