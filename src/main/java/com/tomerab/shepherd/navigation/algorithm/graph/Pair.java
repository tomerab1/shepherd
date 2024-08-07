package com.tomerab.shepherd.navigation.algorithm.graph;

/**
 * Represents a generic key-value pair.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair(" + key + ", " + value + ")";
    }
}
