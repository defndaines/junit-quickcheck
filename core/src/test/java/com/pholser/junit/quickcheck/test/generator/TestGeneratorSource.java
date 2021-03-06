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

package com.pholser.junit.quickcheck.test.generator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.internal.generator.ZilchGenerator;

public class TestGeneratorSource implements Iterable<Generator<?>> {
    @Override public Iterator<Generator<?>> iterator() {
        List<Generator<?>> generators = Arrays.<Generator<?>> asList(
                new BoxGenerator(),
                new TestArrayListGenerator(),
                new TestBigDecimalGenerator(),
                new TestBigIntegerGenerator(),
                new TestBooleanGenerator(),
                new TestByteGenerator(),
                new TestCallableGenerator(),
                new TestCharacterGenerator(),
                new TestDoubleGenerator(),
                new TestFloatGenerator(),
                new TestHashMapGenerator(),
                new TestIntegerGenerator(),
                new TestLongGenerator(),
                new TestShortGenerator(),
                new TestStringGenerator(),
                new ZilchGenerator());

        return generators.iterator();
    }
}
