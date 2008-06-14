/*
 * Copyright (c) 2008, Stephen Colebourne & Michael Nascimento Santos
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
package javax.time.calendar.format;

import java.io.IOException;
import java.util.Locale;

import javax.time.calendar.FlexiDateTime;

/**
 * Prints or parses a string literal.
 *
 * @author Stephen Colebourne
 */
class StringLiteralPrinter implements DateTimePrinter, DateTimeParser {

    /**
     * The literal to print or parse.
     */
    private final String literal;

    /**
     * Constructor.
     *
     * @param literal  the literal to print or parse, not null
     */
    StringLiteralPrinter(String literal) {
        this.literal = literal;
    }

    /** {@inheritDoc} */
    public void print(Appendable appendable, FlexiDateTime dateTime, Locale locale) throws IOException {
        appendable.append(literal);
    }

//    /** {@inheritDoc} */
//    public FlexiDateTime parse(CharSequence parseText, ParsePosition parsePosition, FlexiDateTime dateTime, Locale locale) {
//        int length = parseText.length();
//        int pos = parsePosition.getIndex();
//        int endPos = pos + literal.length();
//        if (endPos > length) {
//            parsePosition.setErrorIndex(pos);
//        } else {
//            if (literal.contentEquals(parseText.subSequence(pos, endPos))) {
//                parsePosition.setIndex(endPos);
//            } else {
//                parsePosition.setErrorIndex(pos);
//            }
//        }
//        return dateTime;
//    }

    /** {@inheritDoc} */
    public int parse(DateTimeParseContext context, String parseText, int position) {
        int length = parseText.length();
        int endPos = position + literal.length();
        if (endPos > length) {
            return ~position;
        }
        if (literal.equals(parseText.substring(position, endPos)) == false) {
            return ~position;
        }
        return endPos;
    }

}
