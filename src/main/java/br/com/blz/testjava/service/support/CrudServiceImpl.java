package br.com.blz.testjava.service.support;

import br.com.blz.testjava.dto.support.PersistableDto;
import br.com.blz.testjava.exception.CustomRuntimeException;
import br.com.blz.testjava.exception.ValidationMsg;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.nonNull;

@Slf4j
public abstract class CrudServiceImpl<R extends MongoRepository<T, I>, T extends Persistable<I>, I, D extends PersistableDto<I>> implements CrudService<I, D> {

    protected final R repository;

    protected final Class<T> entityClass;

    protected final String entityName;

    protected final Class<D> dtoClass;

    protected final ModelMapper modelMapper;

    public CrudServiceImpl(@NonNull R repository, @NonNull Class<T> entityClass, @NonNull Class<D> dtoClass, @NonNull ModelMapper modelMapper) {
        this.repository = repository;
        this.entityClass = entityClass;
        this.entityName = entityClass.getSimpleName();
        this.dtoClass = dtoClass;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public D create(@NonNull D dto) {
        logInfo("Create '{}' with ID: '{}'", entityName, dto.getId());
        var entity = convertToEntity(dto);
        return convertToDto(repository.insert(entity));
    }

    @Transactional
    @Override
    public D update(I id, @NonNull D dto) {
        throwsException(nonNull(id), ValidationMsg.ENTITY_WITHOUT_ID, entityName);
        logInfo("Update '{}' with ID: '{}'", entityName, id);

        T entity = repository.findById(id)
                             .orElseThrow(() -> {throw new CustomRuntimeException(ValidationMsg.ENTITY_NO_EXISTS, entityName, id.toString());});

        modelMapper.map(dto, entity);
        return convertToDto(repository.save(entity));
    }

    @Override
    public Optional<D> findById(@NonNull I id) {
        logInfo("Find '{}' with ID: '{}'", entityName, id);
        return repository.findById(id).map(this::convertToDto);
    }

    @Override
    public Page<D> findAll(@NonNull Pageable pageable) {
        logInfo("Find all {} with pagiation: {}", entityName + "s", pageable);
        return repository.findAll(pageable).map(this::convertToDto);
    }

    @Override
    public Optional<Boolean> deleteById(@NonNull I id) {
        logInfo("Delete '{}' with ID: '{}'", entityName, id);
        try {
            repository.deleteById(id);
            return Optional.of(true);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    protected D convertToDto(@NonNull T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    protected T convertToEntity(@NonNull D dto) {
        return modelMapper.map(dto, entityClass);
    }

    protected static void throwsException(boolean condition, ValidationMsg msgFailCondition, Object... params) {
        if (!condition) {
            throw new CustomRuntimeException(msgFailCondition, params);
        }
    }

    protected static void logInfo(@NonNull String format, Object... args) {
        log.info(format + " (" + LocalDateTime.now() + ")", args);
    }

}
