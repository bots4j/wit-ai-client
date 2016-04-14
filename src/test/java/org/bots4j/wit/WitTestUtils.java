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

/**
 * Created by ajchesney on 14/04/2016.
 */
public class WitTestUtils {


    /**
     * Switch between WireMock proxy and stub
     * based on the presence of a real app token
     * in system props
     */
    public static WitClient witClient(){
        String url = "http://localhost:8080";
        String appToken = System.getProperty(WitClient.APP_TOKEN_KEY);
        if ( appToken == null ){
            appToken = "FAKE_APP_SECRET";
            url = "http://localhost:8181";
        }
        return new WitClient(url,appToken);
    }

}
