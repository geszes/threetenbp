/*
 * Copyright (c) 2012, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javax.time.builder.chrono;

import static javax.time.builder.LocalDateUnit.DAYS;
import static javax.time.builder.LocalDateUnit.ERAS;
import static javax.time.builder.LocalDateUnit.FOREVER;
import static javax.time.builder.LocalDateUnit.MONTHS;
import static javax.time.builder.LocalDateUnit.WEEKS;
import static javax.time.builder.LocalDateUnit.YEARS;

import javax.time.builder.PeriodUnit;

/**
 * The set of fields that can be accessed using a chronology.
 * <p>
 * The set of fields used by other calendar systems is limited to those defined here.
 */
public enum ChronoField {

    DAY_OF_WEEK("ChronoDayOfWeek", DAYS, WEEKS),
    DAY_OF_MONTH("ChronoDayOfMonth", DAYS, MONTHS),
    DAY_OF_YEAR("ChronoDayOfYear", DAYS, YEARS),
    EPOCH_DAY("EpochDay", DAYS, FOREVER),
    MONTH_OF_YEAR("ChronoMonthOfYear", MONTHS, YEARS),
    YEAR_OF_ERA("ChronoYearOfEra", YEARS, ERAS),
    PROLEPTIC_YEAR("ChronoProlepticYear", YEARS, FOREVER),
    ERA("ChronoEra", ERAS, FOREVER);

    private final String name;
    private final PeriodUnit baseUnit;
    private final PeriodUnit rangeUnit;

    private ChronoField(String name, PeriodUnit baseUnit, PeriodUnit rangeUnit) {
        this.name = name;
        this.baseUnit = baseUnit;
        this.rangeUnit = rangeUnit;
    }

    public String getName() {
        return name;
    }

    public PeriodUnit getBaseUnit() {
        return baseUnit;
    }

    public PeriodUnit getRangeUnit() {
        return rangeUnit;
    }

    @Override
    public String toString() {
        return getName();
    }

}