package com.theopendle.spring.demo.jersey;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

/**
 * Wraps a collection in order to avoid the JSON array CSRF qttqck described in this article:
 * https://blog.jeremiahgrossman.com/2006/01/advanced-web-attack-techniques-using.html
 *
 * @param <T> the collection generic type
 * @author Theo Pendle
 */
@Data
@NoArgsConstructor
public class SafeCollection<T> implements Serializable {

    private static final long serialVersionUID = -6606337108567268883L;

    @JsonProperty("list")
    private Collection<T> collection;

    public SafeCollection(final Collection<T> collection) {
        this.collection = collection;
    }
}
