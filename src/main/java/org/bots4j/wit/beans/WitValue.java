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
import java.util.Arrays;
import java.util.List;

/**
 * Created by ajchesney on 13/04/2016.
 */
public class WitValue {

    @Key
    private String value;
    @Key
    private List<String> expressions = new ArrayList<>();

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public WitValue withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     *
     * @return
     * The expressions
     */
    public List<String> getExpressions() {
        return expressions;
    }

    /**
     *
     * @param expressions
     * The expressions
     */
    public void setExpressions(List<String> expressions) {
        this.expressions = expressions;
    }

    public WitValue withExpressions(List<String> expressions) {
        this.expressions = expressions;
        return this;
    }

    public WitValue withExpressions(String... expressions){
        return withExpressions(Arrays.asList(expressions));
    }

}
