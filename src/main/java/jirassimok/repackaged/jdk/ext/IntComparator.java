/*
 * Copyright (c) 2009, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package jirassimok.repackaged.jdk.ext;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;

@FunctionalInterface
public interface IntComparator
{
    /**
     * Negative if first argument is smaller, positive if first argument is larger.
     */
    int compare(int i1, int i2);

    public static <U> IntComparator comparing(
            IntFunction<? extends U> keyExtractor,
            Comparator<? super U> keyComparator)
    {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return (c1, c2) -> keyComparator.compare(
                keyExtractor.apply(c1),
                keyExtractor.apply(c2));
    }

    public static <T> IntComparator comparingLong(IntToLongFunction keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (c1, c2) -> Long.compare(
                keyExtractor.applyAsLong(c1),
                keyExtractor.applyAsLong(c2));
    }

    public static<T> IntComparator comparingDouble(IntToDoubleFunction keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return (c1, c2) -> Double.compare(
                keyExtractor.applyAsDouble(c1),
                keyExtractor.applyAsDouble(c2));
    }
}
