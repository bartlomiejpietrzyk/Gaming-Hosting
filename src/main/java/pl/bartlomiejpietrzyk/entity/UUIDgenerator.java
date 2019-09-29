package pl.bartlomiejpietrzyk.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
abstract class UUIDgenerator {

    @Column(unique = true)
    private String uuid = UUID.randomUUID().toString();

    @Override
    public final int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public final boolean equals(Object obj) {

        if (!(obj instanceof UUIDgenerator)) {
            return false;
        }
        UUIDgenerator that = (UUIDgenerator) obj;
        return Objects.equals(this.uuid, that.uuid);
    }
}