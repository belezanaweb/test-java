package br.com.blz.testjava.service;

import br.com.blz.testjava.config.ModelMapperConfig;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.CustomRuntimeException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import static br.com.blz.testjava.TestUtils.getProduct;
import static br.com.blz.testjava.TestUtils.getProductDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Spy
    private final ModelMapper modelMapper = ModelMapperConfig.getModelMapper();

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        given(repository.existsById(anyLong())).willReturn(false);
        given(repository.insert(any(Product.class))).willAnswer(invocation -> invocation.getArgument(0));

        var expected = service.create(getProductDto(1L, "L'Oréal Professionnel"));

        assertThat(expected).isInstanceOf(ProductDto.class);
        verify(repository).existsById(anyLong());
        verify(repository).insert(any(Product.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void create_AlreadyExist() {
        given(repository.existsById(anyLong())).willReturn(true);

        var expected = assertThrows(CustomRuntimeException.class, () ->
            service.create(getProductDto(1L, "L'Oréal Professionnel")));

        assertThat(expected).hasMessage("entity_exists: [Product, SKU, 1]");

        verify(repository).existsById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void update_WithoutId() {
        var expected = assertThrows(CustomRuntimeException.class, () -> service.update(null, new ProductDto()));
        assertThat(expected).hasMessage("entity_without_id: [Product]");

        verifyNoInteractions(repository);
    }

    @Test
    void update_EntityNoExists() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        var expected = assertThrows(CustomRuntimeException.class, () -> service.update(1L, getProductDto(1L, "Loreal")));
        assertThat(expected).hasMessage("entity_no_exists: [Product, 1]");

        verify(repository).findById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findById() {
        given(repository.findById(anyLong())).willReturn(Optional.of(getProduct(123L, "Loreal", 14, 1)));

        var expected = service.findById(1L).orElse(null);
        assertThat(expected).isInstanceOf(ProductDto.class).hasFieldOrPropertyWithValue("id", 123L);

        verify(repository).findById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        given(repository.findAll(any(Pageable.class))).willReturn(PageableExecutionUtils.getPage(List.of(new Product(), new Product()), Pageable.unpaged(), () -> 0));

        var expected = service.findAll(Pageable.unpaged());
        assertThat(expected)
            .isNotEmpty()
            .hasSize(2)
            .hasOnlyElementsOfType(ProductDto.class);

        verify(repository).findAll(any(Pageable.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteById() {
        var expected = service.deleteById(1L);
        assertThat(expected).hasValue(true);

        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }

}
