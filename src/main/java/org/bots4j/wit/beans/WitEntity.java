package org.bots4j.wit.beans;

/*
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

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajchesney on 13/04/2016.
 */
public class WitEntity {

    @Key
    private String id;
    @Key
    private List<WitValue> values = new ArrayList<>();

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    public WitEntity withId(String id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The values
     */
    public List<WitValue> getValues() {
        return values;
    }

    /**
     *
     * @param values
     * The values
     */
    public void setValues(List<WitValue> values) {
        this.values = values;
    }

    public WitEntity withValues(List<WitValue> values) {
        this.values = values;
        return this;
    }

}
