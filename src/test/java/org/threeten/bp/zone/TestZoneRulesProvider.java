/*
 * Copyright (c) 2007-2013, Stephen Colebourne & Michael Nascimento Santos
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
package org.threeten.bp.zone;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.testng.annotations.Test;
import org.threeten.bp.ZoneOffset;

/**
 * Test ZoneRulesProvider.
 */
@Test
public class TestZoneRulesProvider {

    //-----------------------------------------------------------------------
    // getAvailableZoneIds()
    //-----------------------------------------------------------------------
    @Test
    public void test_getAvailableGroupIds() {
        Set<String> zoneIds = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(zoneIds.contains("Europe/London"), true);
        zoneIds.clear();
        assertEquals(zoneIds.size(), 0);
        Set<String> zoneIds2 = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(zoneIds2.contains("Europe/London"), true);
    }

    //-----------------------------------------------------------------------
    // getRules(String)
    //-----------------------------------------------------------------------
    @Test
    public void test_getRules_String() {
        ZoneRules rules = ZoneRulesProvider.getRules("Europe/London");
        assertNotNull(rules);
        ZoneRules rules2 = ZoneRulesProvider.getRules("Europe/London");
        assertEquals(rules2, rules);
    }

    @Test(expectedExceptions=ZoneRulesException.class)
    public void test_getRules_String_unknownId() {
        ZoneRulesProvider.getRules("Europe/Lon");
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void test_getRules_String_null() {
        ZoneRulesProvider.getRules(null);
    }

    //-----------------------------------------------------------------------
    // getVersions(String)
    //-----------------------------------------------------------------------
    @Test
    public void test_getVersions_String() {
        NavigableMap<String, ZoneRules> versions = ZoneRulesProvider.getVersions("Europe/London");
        assertTrue(versions.size() >= 1);
        ZoneRules rules = ZoneRulesProvider.getRules("Europe/London");
        assertEquals(versions.lastEntry().getValue(), rules);

        NavigableMap<String, ZoneRules> copy = new TreeMap<>(versions);
        versions.clear();
        assertEquals(versions.size(), 0);
        NavigableMap<String, ZoneRules> versions2 = ZoneRulesProvider.getVersions("Europe/London");
        assertEquals(versions2, copy);
    }

    @Test(expectedExceptions=ZoneRulesException.class)
    public void test_getVersions_String_unknownId() {
        ZoneRulesProvider.getVersions("Europe/Lon");
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void test_getVersions_String_null() {
        ZoneRulesProvider.getVersions(null);
    }

    //-----------------------------------------------------------------------
    // refresh()
    //-----------------------------------------------------------------------
    @Test
    public void test_refresh() {
        assertEquals(ZoneRulesProvider.refresh(), false);
    }

    //-----------------------------------------------------------------------
    // registerProvider()
    //-----------------------------------------------------------------------
    @Test
    public void test_registerProvider() {
        Set<String> pre = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(pre.contains("FooLocation"), false);
        ZoneRulesProvider.registerProvider(new MockTempProvider());
        assertEquals(pre.contains("FooLocation"), false);
        Set<String> post = ZoneRulesProvider.getAvailableZoneIds();
        assertEquals(post.contains("FooLocation"), true);

        assertEquals(ZoneRulesProvider.getRules("FooLocation"), ZoneOffset.of("+01:45").getRules());
    }

    static class MockTempProvider extends ZoneRulesProvider {
        final ZoneRules rules = ZoneOffset.of("+01:45").getRules();
        @Override
        public Set<String> provideZoneIds() {
            return new HashSet<String>(Collections.singleton("FooLocation"));
        }
        @Override
        protected ZoneRulesProvider provideBind(String zoneId) {
            return this;
        }
        @Override
        protected NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
            NavigableMap<String, ZoneRules> result = new TreeMap<>();
            result.put("BarVersion", rules);
            return result;
        }
        @Override
        protected ZoneRules provideRules(String zoneId) {
            if (zoneId.equals("FooLocation")) {
                return rules;
            }
            throw new ZoneRulesException("Invalid");
        }
    }

}
