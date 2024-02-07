package com.systex.ddd.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(type = "string")
public class UUID implements Serializable {

    private static final long serialVersionUID = -4736096267454071379L;

    @Column(name = "uuid", nullable = false)
    private String value;

    private UUID(Class<?> clazz) {
        this.value = clazz.getSimpleName()
                          .toLowerCase() + "_" + java.util.UUID.randomUUID()
                                                               .toString()
                                                               .toUpperCase();
        this.value = this.value.replace("-", "");
    }

    public static UUID generateUUID(Class<?> clazz) {
        return new UUID(clazz);
    }

    public static UUID generateUUID(String id) {
        if (id != null && !id.trim().isEmpty()) {
            UUID uuid = new UUID();
            uuid.value = id;
            return uuid;
        }

        return null;
    }
}
