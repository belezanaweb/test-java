package br.com.blz.testjava.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Contract for a Converter
 *
 * @param <I> Input. Source object.
 * @param <O> Output. Result of the conversion.
 */
public interface Converter<I, O> {

    O perform(I input);

    default Collection<O> perform(Collection<I> input) {
        if (input == null) {
            return Collections.emptyList();
        }

        return input.stream()
            .map(this::perform)
            .collect(Collectors.toList());
    }
}
