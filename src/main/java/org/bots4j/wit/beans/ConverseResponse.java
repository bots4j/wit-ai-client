package org.bots4j.wit.beans;/*
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

import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.Key;

import org.bots4j.utils.json.FlexibleMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ajchesney on 13/04/2016.
 */
public class ConverseResponse {

    @Key
    private String type;
    @Key
    private String action;
    @Key
    private String msg;
    @Key
    private EntityMap entities = new EntityMap();
    @Key
    private Double confidence;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public ConverseResponse withType(String type) {
        this.type = type;
        return this;
    }

    /**
     *
     * @return
     * The action
     */
    public String getAction() {
        return action;
    }

    /**
     *
     * @param action
     * The action
     */
    public void setAction(String action) {
        this.action = action;
    }

    public ConverseResponse withAction(String action) {
        this.action = action;
        return this;
    }

    /**
     *
     * @return
     * The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     * The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ConverseResponse withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     *
     * @return
     * The entities
     */
    public EntityMap getEntities() {
        return entities;
    }

    /**
     *
     * @param entities
     * The entities
     */
    public void setEntities(EntityMap entities) {
        this.entities = entities;
    }

    public ConverseResponse withEntities(EntityMap entities) {
        this.entities = entities;
        return this;
    }

    /**
     *
     * @return
     * The confidence
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     *
     * @param confidence
     * The confidence
     */
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public ConverseResponse withConfidence(Double confidence) {
        this.confidence = confidence;
        return this;
    }
}
