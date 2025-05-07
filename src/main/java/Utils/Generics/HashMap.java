package Utils.Generics;

import java.util.LinkedList;

public class HashMap<KData, VData> {

    private static class Entry<KData, VData> {
        KData key;
        VData value;

        Entry(KData key, VData value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<KData, VData>>[] buckets;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new LinkedList[INITIAL_CAPACITY];
    }

    private int getBucketIndex(KData key) {
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    public void put(KData key, VData value) {
        int index = getBucketIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (Entry<KData, VData> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    public VData get(KData key) {
        int index = getBucketIndex(key);
        LinkedList<Entry<KData, VData>> bucket = buckets[index];

        if (bucket == null) return null;

        for (Entry<KData, VData> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean containsKey(KData key) {
        return get(key) != null;
    }

    public void remove(KData key) {
        int index = getBucketIndex(key);
        LinkedList<Entry<KData, VData>> bucket = buckets[index];

        if (bucket == null) return;

        for (Entry<KData, VData> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                break;
            }
        }
    }
    public List<VData> values() {
        List<VData> valueList = new List<>();

        for (LinkedList<Entry<KData, VData>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<KData, VData> entry : bucket) {
                    valueList.add(entry.value);
                }
            }
        }

        return valueList;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        for (int i = 0; i < buckets.length; i++) {
            LinkedList<Entry<KData, VData>> bucket = buckets[i];
            if (bucket != null) {
                for (Entry<KData, VData> entry : bucket) {
                    System.out.println(entry.key + " => " + entry.value);
                }
            }
        }
    }
    public VData getOrDefault(KData key, VData defaultValue) {
        VData value = get(key);
        return (value != null) ? value : defaultValue;
    }

}
