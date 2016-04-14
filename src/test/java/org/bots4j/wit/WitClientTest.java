package org.bots4j.wit;/*
 * Copyright (C) 2016 Adam J Chesney
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.bots4j.wit.beans.ConverseResponse;
import org.bots4j.wit.beans.GetIntentViaTextResponse;
import org.bots4j.wit.beans.Outcome;
import org.bots4j.wit.beans.WitContext;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.bots4j.wit.WitTestUtils.witClient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ajchesney on 14/04/2016.
 */
public class WitClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8181);

    @Test
    public void testGetIntentViaText(){

        WitClient client = witClient();

        String message = "i want to be in paris on the 3rd of may?";

        GetIntentViaTextResponse response = client.getIntentViaText(message,null,null,null,null);
        assertNotNull(response.getOutcomes());

        Outcome outcome = response.getOutcomes().get(0);
        assertEquals("book-trip", outcome.getEntities().firstEntityValue("intent"));
        assertEquals("paris", outcome.getEntities().firstEntityValue("location"));
    }

    @Test
    public void testConverse(){

        WitClient client = witClient();

        String message = "i need to go to stockholm on monday and come back on wednesday";
        WitContext ctx = new WitContext();

        ConverseResponse response = client.converse("test-converse-id1",message,ctx);
        assertEquals("merge",response.getType());

        String intent = response.getEntities().firstEntityValue("intent");
        assertEquals("book-trip", intent);

        if ( response.getEntities().containsKey("location") ){
            ctx.put("destination",response.getEntities().firstEntityValue("location"));
        }

        List<String> dates = response.getEntities().entityValues("datetime");
        if ( dates != null && !dates.isEmpty() ){
            ctx.put("arrivalDate", dates.get(0));
            if ( dates.size() > 1 ){
                ctx.put("returnDate", dates.get(1));
            }
        }

        response = client.converse("test-converse-id1",null,ctx);
        assertEquals("msg",response.getType());

        // required so that wire mock can tell the difference
        ctx.put("ignoreme","please");
        response = client.converse("test-converse-id1",null,ctx);
        assertEquals("action",response.getType());

        assertEquals("findFlights", response.getAction());
    }


}
