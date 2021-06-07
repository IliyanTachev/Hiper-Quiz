package dao;

@FunctionalInterface
public interface KeyGenerator<K> {
    K getNextId();
}

