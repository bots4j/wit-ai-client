/*
 *
 *  * Copyright (C) 2016 Adam J Chesney
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.bots4j.wit;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bots4j.utils.json.GsonUtils;
import org.bots4j.wit.beans.ConverseResponse;
import org.bots4j.wit.beans.GetIntentViaTextResponse;
import org.bots4j.wit.beans.WitContext;


import java.io.IOException;
import java.net.URI;

import javax.annotation.Nullable;

/**
 * Client for interacting with Wit.ai Bot Engine
 *
 *
 * Created by ajchesney on 11/04/2016.
 */
public class WitClient {

    private final static Logger logger = LogManager.getLogger(WitClient.class);
    public static final String APP_TOKEN_KEY = "org.bots4j.witai.token";

    private static final String BASE_URL = "https://api.wit.ai";
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String VERSION = "20160413";

    private String baseUrl;
    private String appToken;

    private HttpRequestFactory requestFactory;

    /**
     * Create client and look for Wit App Token in system prop: org.bots4j.witai.token
     */
    public WitClient(){
        this(validateSystemPropertyExists(APP_TOKEN_KEY));
    }

    /**
     * Create client with the given wit ai app token
     * @param appToken
     */
    public WitClient(String appToken){
        this(BASE_URL, appToken);
    }

    /**
     * Create client using non-default baseUrl and given app token
     * This is useful for testing etc
     * @param baseUrl
     * @param appToken
     */
    public WitClient(String baseUrl, String appToken){
        this.baseUrl = baseUrl;
        this.appToken = appToken;
        requestFactory = createHttpRequestFactory();
    }

    /**
     * Returns what your bot should do next.
     * The next step can be either answering to the user,
     * performing an action, or waiting for further requests.
     *
     * @param sessionId
     * @param messageFromUser
     * @param context
     * @return
     */
    public ConverseResponse converse(String sessionId,
                                     @Nullable String messageFromUser,
                                     @Nullable WitContext context){
        GenericUrl url = url("/converse").set("session_id", sessionId);
        if (messageFromUser!=null){
            url.set("q",messageFromUser);
        }
        if (context!=null){
            url.set("context", GsonUtils.toJson(context));
        }
        return postRequest(url,ConverseResponse.class);
    }

    /**
     * Returns the extracted meaning from a sentence, based on the app data.
     *
     * @param messageFromUser - query text provided by user
     * @param context - @WitContext containing context properties
     * @param messageId - Your id for this getIntentViaText if you have one
     * @param threadId - Your id for this conversation if you have one
     * @param numberOfBestOutcomes
     * @return @GetIntentViaTextResponse containing probable intents
     */
    public GetIntentViaTextResponse getIntentViaText(String messageFromUser,
                                                     @Nullable WitContext context,
                                                     @Nullable String messageId,
                                                     @Nullable String threadId,
                                                     @Nullable Integer numberOfBestOutcomes){

        GenericUrl url = url("/message").set("q", messageFromUser);
        if (context!=null){
            url.set("context", GsonUtils.toJson(context));
        }
        if (messageId!=null){
            url.set("msg_id", messageId);
        }
        if (threadId!=null){
            url.set("thread_id",threadId);
        }
        if(numberOfBestOutcomes!=null){
            url.set("n",numberOfBestOutcomes);
        }
        return getRequest(url,GetIntentViaTextResponse.class);
    }

    protected GenericUrl url(String path){
        URI uri = uri(path);
        return new GenericUrl(uri).set("v",VERSION);
    }

    protected <E> E postRequest(GenericUrl url, Class<E> responseType){
        try {
            if ( logger.isDebugEnabled() ){
                logger.debug("Request POSTed into wit.ai api " + url.toString());
            }
            HttpRequest request = requestFactory.buildPostRequest(url,null);
            return parseResponse(request,responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected <E> E getRequest(GenericUrl url, Class<E> responseType){
        try {
            if ( logger.isDebugEnabled() ){
                logger.debug("Request POSTed into wit.ai api " + url.toString());
            }
            HttpRequest request = requestFactory.buildGetRequest(url);
            return parseResponse(request, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <E> E parseResponse(HttpRequest request, Class<E> responseType) throws IOException {
        E response = (E) request.execute().parseAs(responseType);
        if ( logger.isDebugEnabled() ){
            logger.debug("Response back from wit.ai api:");
            logger.debug(JSON_FACTORY.toPrettyString(response));
        }
        return response;
    }

    private URI uri(String path) {
        String str = baseUrl;
        if ( str.endsWith("/") && path.startsWith("/") ){
            str += path.substring(1);
        }
        else if (baseUrl.endsWith("/") || path.startsWith("/") ){
            str += path;
        }
        else {
            str += "/" + path;
        }
        return URI.create(str);
    }

    protected HttpRequestFactory createHttpRequestFactory() {
        return HTTP_TRANSPORT.createRequestFactory(request -> {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
            request.getHeaders().setAuthorization("Bearer " + appToken);
            request.getHeaders().setAccept("application/vnd.wit." + VERSION +"+json");
        });
    }

    private static String validateSystemPropertyExists(String key){
        String result = System.getProperty(key);
        if (result==null){
            throw new RuntimeException("No System Property found for key: " + key);
        }
        return result;
    }

}
