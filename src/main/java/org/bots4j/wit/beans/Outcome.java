
package org.bots4j.wit.beans;

import com.google.api.client.util.Key;

import org.bots4j.utils.json.FlexibleMap;

public class Outcome {

    @Key("_text")
    private String text;
    @Key
    private String intent;
    @Key
    private EntityMap entities = new EntityMap();
    @Key
    private Double confidence;

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param Text
     *     The _text
     */
    public void setText(String Text) {
        this.text = Text;
    }

    public Outcome withText(String Text) {
        this.text = Text;
        return this;
    }

    /**
     * 
     * @return
     *     The intent
     */
    public String getIntent() {
        return intent;
    }

    /**
     * 
     * @param intent
     *     The intent
     */
    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Outcome withIntent(String intent) {
        this.intent = intent;
        return this;
    }

    /**
     * 
     * @return
     *     The entities
     */
    public EntityMap getEntities() {
        return entities;
    }

    /**
     * 
     * @param entities
     *     The entities
     */
    public void setEntities(EntityMap entities) {
        this.entities = entities;
    }

    public Outcome withEntities(EntityMap entities) {
        this.entities = entities;
        return this;
    }

    /**
     * 
     * @return
     *     The confidence
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * 
     * @param confidence
     *     The confidence
     */
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Outcome withConfidence(Double confidence) {
        this.confidence = confidence;
        return this;
    }

}
