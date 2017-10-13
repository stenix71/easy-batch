/**
 * The MIT License
 *
 *   Copyright (c) 2017, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package org.easybatch.core.skipper;

import org.easybatch.core.record.StringRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndWithStringRecordSkipperTest {

    @Mock
    private StringRecord stringRecord;

    private EndWithStringRecordSkipper endWithStringRecordSkipper;

    @Test
    public void whenTheRecordEndsWithExpectedSuffix_ThenItShouldBeSkipped() {
        when(stringRecord.getPayload()).thenReturn("content_suffix");
        endWithStringRecordSkipper = new EndWithStringRecordSkipper("suffix");
        assertThat(endWithStringRecordSkipper.processRecord(stringRecord)).isNull();
    }

    @Test
    public void whenTheRecordDoesNotEndWithExpectedSuffix_ThenItShouldNotBeSkipped() {
        when(stringRecord.getPayload()).thenReturn("content");
        endWithStringRecordSkipper = new EndWithStringRecordSkipper("suffix");
        assertThat(endWithStringRecordSkipper.processRecord(stringRecord)).isEqualTo(stringRecord);
    }

    @Test
    public void whenTheRecordEndsWithOneOfTheExpectedSuffixes_ThenItShouldBeSkipped() {
        when(stringRecord.getPayload()).thenReturn("content_suffix1");
        endWithStringRecordSkipper = new EndWithStringRecordSkipper("suffix1", "suffix2");
        assertThat(endWithStringRecordSkipper.processRecord(stringRecord)).isNull();
    }

    @Test
    public void whenTheRecordDoesNotEndWithOneOfTheExpectedSuffixes_ThenItShouldNotBeSkipped() {
        when(stringRecord.getPayload()).thenReturn("content");
        endWithStringRecordSkipper = new EndWithStringRecordSkipper("suffix1", "suffix2");
        assertThat(endWithStringRecordSkipper.processRecord(stringRecord)).isEqualTo(stringRecord);
    }

}