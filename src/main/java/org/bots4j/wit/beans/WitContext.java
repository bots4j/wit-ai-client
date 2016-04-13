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

package org.bots4j.wit.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.api.client.util.Key;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WitContext {

    @Key
    private List<String> state = new ArrayList<String>();
    @Key
    private String referenceTime;
    @Key
    private String timezone;
    @Key
    private List<WitEntity> entities = new ArrayList<WitEntity>();

    /**
     *
     * @return
     * The state
     */
    public List<String> getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(List<String> state) {
        this.state = state;
    }

    public WitContext withState(List<String> state) {
        this.state = state;
        return this;
    }

    /**
     *
     * @return
     * The referenceTime
     */
    public String getReferenceTime() {
        return referenceTime;
    }

    /**
     *
     * @param referenceTime
     * The reference_time
     */
    public void setReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
    }

    public WitContext withReferenceTime(String referenceTime) {
        this.referenceTime = referenceTime;
        return this;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public WitContext withTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     *
     * @return
     * The entities
     */
    public List<WitEntity> getEntities() {
        return entities;
    }

    /**
     *
     * @param entities
     * The entities
     */
    public void setEntities(List<WitEntity> entities) {
        this.entities = entities;
    }

    public WitContext withEntities(List<WitEntity> entities) {
        this.entities = entities;
        return this;
    }

}
