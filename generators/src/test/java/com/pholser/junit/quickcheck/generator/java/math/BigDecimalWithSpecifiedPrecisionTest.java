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

package com.pholser.junit.quickcheck.generator.java.math;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.pholser.junit.quickcheck.generator.BasicGeneratorTheoryParameterTest;
import com.pholser.junit.quickcheck.generator.Precision;

import static java.math.BigDecimal.*;
import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

public class BigDecimalWithSpecifiedPrecisionTest extends BasicGeneratorTheoryParameterTest {
    private BigDecimal first;
    private BigDecimal second;
    private BigDecimal third;

    @Override protected void primeSourceOfRandomness() {
        first = TEN.subtract(TEN.negate()).movePointRight(5);
        second = TEN.pow(2).subtract(TEN.pow(2).negate()).movePointRight(5);
        third = TEN.pow(3).subtract(TEN.pow(3).negate()).movePointRight(5);
        when(randomForParameterGenerator.nextBigInteger(first.toBigInteger().bitLength()))
            .thenReturn(new BigInteger("999999999999")).thenReturn(BigInteger.ONE);
        when(randomForParameterGenerator.nextBigInteger(second.toBigInteger().bitLength()))
            .thenReturn(new BigInteger("136"));
        when(randomForParameterGenerator.nextBigInteger(third.toBigInteger().bitLength()))
            .thenReturn(new BigInteger("768"));
    }

    @Override protected Type parameterType() {
        return BigDecimal.class;
    }

    @Override protected int sampleSize() {
        return 3;
    }

    @Override protected List<?> randomValues() {
        return asList(new BigDecimal("-9.99999"), new BigDecimal("-99.99864"), new BigDecimal("-999.99232"));
    }

    @Override protected Map<Class<? extends Annotation>, Annotation> configurations() {
        Precision precision = mock(Precision.class);
        when(precision.scale()).thenReturn(5);
        return Collections.<Class<? extends Annotation>, Annotation> singletonMap(Precision.class, precision);
    }

    @Override public void verifyInteractionWithRandomness() {
        verify(randomForParameterGenerator, times(2)).nextBigInteger(first.toBigInteger().bitLength());
        verify(randomForParameterGenerator).nextBigInteger(second.toBigInteger().bitLength());
        verify(randomForParameterGenerator).nextBigInteger(third.toBigInteger().bitLength());
    }
}
