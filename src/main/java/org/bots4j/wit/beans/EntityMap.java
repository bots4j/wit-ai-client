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

import org.bots4j.utils.json.FlexibleMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ajchesney on 14/04/2016.
 */
public class EntityMap extends FlexibleMap {

    public List<String> entityValues(String entityId){
        List<ArrayMap<String,String>> entity = (List<ArrayMap<String,String>>) get(entityId);
        if ( entity != null ){
            return entity.stream()
                    .map(am -> am.get("value"))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public String firstEntityValue(String entityId){
        List<ArrayMap<String,String>> entity = (List<ArrayMap<String,String>>) get(entityId);
        if ( entity != null ){
            return entity.stream()
                    .map(am -> am.get("value"))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public long count(String entityId){
        List<ArrayMap<String,String>> entity = (List<ArrayMap<String,String>>) get(entityId);
        if ( entity != null ){
            return entity.stream().count();
        }
        return 0l;
    }

}
