package hiperquiz.entities;

public interface Identifiable<K> {
    K getId();
    void setId(K id);
}
