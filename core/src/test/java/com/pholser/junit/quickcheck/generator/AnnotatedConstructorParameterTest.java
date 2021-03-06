/*
 The MIT License

 Copyright (c) 2010-2014 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.generator;

import com.pholser.junit.quickcheck.ForAll;
import com.pholser.junit.quickcheck.SuchThat;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class AnnotatedConstructorParameterTest {
    private AnnotatedConstructorParameter parameter;

    @Before public void setUp() throws Exception {
        parameter =
            new AnnotatedConstructorParameter(Annotated.class.getConstructor(int.class).getParameterAnnotations()[0]);
    }

    @Test public void getAnnotations() {
        Annotation[] annotations = parameter.getAnnotations();

        assertEquals(1, annotations.length);
        assertEquals(ForAll.class, annotations[0].annotationType());
    }

    @Test public void findingSpecificAnnotation() {
        assertEquals(50, parameter.getAnnotation(ForAll.class).sampleSize());
    }

    @Test public void missingSpecificAnnotation() {
        assertNull(parameter.getAnnotation(SuchThat.class));
    }

    @Test public void getDeclaredAnnotations() {
        Annotation[] annotations = parameter.getDeclaredAnnotations();

        assertEquals(1, annotations.length);
        assertEquals(ForAll.class, annotations[0].annotationType());
    }

    @Test public void annotationPresent() {
        assertTrue(parameter.isAnnotationPresent(ForAll.class));
    }

    @Test public void annotationAbsent() {
        assertFalse(parameter.isAnnotationPresent(SuchThat.class));
    }

    public static class Annotated {
        public Annotated(@ForAll(sampleSize = 50) int i) {
        }
    }
}
