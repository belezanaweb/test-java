package br.com.blz.testjava.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.getConfiguration()
                    .setMatchingStrategy(MatchingStrategies.STRICT)
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE)
                    .setSkipNullEnabled(true)
                    .getConverters().add(new FromOptionalConverter());
    }

    @Bean
    public ModelMapper modelMapper() {
        return MODEL_MAPPER;
    }

    public static ModelMapper getModelMapper() {
        return MODEL_MAPPER;
    }

    /**
     * https://github.com/modelmapper/modelmapper-module-java8/blob/master/datatypes/src/main/java/org/modelmapper/module/jdk8/FromOptionalConverter
     * .java
     * Converts  {@link Optional} to {@link Object}
     *
     * @author Chun Han Hsiao
     */
    private static class FromOptionalConverter implements ConditionalConverter<Optional<Object>, Object> {

        @Override
        public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
            return Optional.class == sourceType && Optional.class != destinationType ? MatchResult.FULL : MatchResult.NONE;
        }

        @Override
        public Object convert(MappingContext<Optional<Object>, Object> context) {
            var source = context.getSource();
            if (source.isEmpty()) {
                return null;
            }

            var mappingContext = context.create(source.get(), context.getDestinationType());
            return context.getMappingEngine().map(mappingContext);
        }

    }

}
