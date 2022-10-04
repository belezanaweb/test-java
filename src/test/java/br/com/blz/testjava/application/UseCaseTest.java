package br.com.blz.testjava.application;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public abstract class UseCaseTest implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) {
        Mockito.reset(getMocks().toArray());
    }

    protected abstract List<Object> getMocks();

}
