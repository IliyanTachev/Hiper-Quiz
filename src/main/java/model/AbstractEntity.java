package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class AbstractEntity<K extends Comparable<K>, V extends Identifiable<K>> implements Identifiable<K>, Comparable<V>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified = new Date();

    public AbstractEntity() {
    }

    @Override
    public K getId() {
        return id;
    }

    @Override
    public void setId(K id) {
        this.id = id;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<?, ?> that = (AbstractEntity<?, ?>) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @Override
    public int compareTo(V other) {
        return this.getId().compareTo(other.getId()); // to test in reverse if extends Comparable is needed
    }
}
