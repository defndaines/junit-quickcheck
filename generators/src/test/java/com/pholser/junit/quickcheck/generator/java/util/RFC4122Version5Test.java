package com.pholser.junit.quickcheck.generator.java.util;

import com.pholser.junit.quickcheck.Generating;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.internal.generator.CoreTheoryParameterTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.pholser.junit.quickcheck.Generating.*;
import static com.pholser.junit.quickcheck.generator.java.util.RFC4122.*;
import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class RFC4122Version5Test extends CoreTheoryParameterTest {
    @SuppressWarnings("unchecked")
    @Override protected Class<? extends Generator>[] explicitGenerators() {
        return (Class<? extends Generator>[]) new Class<?>[] { Version5.class };
    }

    @Override protected Map<Class<? extends Annotation>, Annotation> configurations() {
        Namespace namespace = mock(Namespace.class);
        when(namespace.value()).thenReturn(Namespaces.DNS);
        return Collections.<Class<? extends Annotation>, Annotation> singletonMap(Namespace.class, namespace);
    }

    @Override protected void primeSourceOfRandomness() throws Exception {
        when(Generating.charsForString(randomForParameterGenerator))
            .thenReturn(0x61).thenReturn(0x62).thenReturn(0x63).thenReturn(0x64).thenReturn(0x65).thenReturn(0x66);
    }

    @Override protected Type parameterType() {
        return UUID.class;
    }

    @Override protected int sampleSize() {
        return 4;
    }

    @Override protected List<?> randomValues() {
        return asList(UUID.fromString("4ebd0208-8328-5d69-8c44-ec50939c0967"),
            UUID.fromString("4f3f2898-69e3-5a0d-820a-c4e87987dbce"),
            UUID.fromString("656c78e8-4631-5bef-9c7f-e2795ecee6d6"),
            UUID.fromString("96aa5957-8466-5a38-9bbb-adf7eab336c9"));
    }

    @Override public void verifyInteractionWithRandomness() {
        verifyCharsForString(randomForParameterGenerator, times(6));
    }
}
