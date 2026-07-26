import java.util.LinkedList;

class MyHashSet {
    // Choose a primary number for the bucket size to minimize collision
    private final int BUCKET_SIZE = 769; 
    private LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[BUCKET_SIZE];
        for (int i = 0; i < BUCKET_SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Helper method to compute the bucket index for a key
    private int hash(int key) {
        return key % BUCKET_SIZE;
    }

    public void add(int key) {
        int index = hash(key);
        LinkedList<Integer> bucket = buckets[index];
        if (!bucket.contains(key)) {
            bucket.add(key);
        }
    }

    public void remove(int key) {
        int index = hash(key);
        LinkedList<Integer> bucket = buckets[index];
        // Integer.valueOf(key) ensures object removal instead of index removal
        bucket.remove(Integer.valueOf(key)); 
    }

    public boolean contains(int key) {
        int index = hash(key);
        LinkedList<Integer> bucket = buckets[index];
        return bucket.contains(key);
    }
}
