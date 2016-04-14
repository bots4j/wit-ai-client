package org.bots4j.wit.beans;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class GetIntentViaTextResponse {

    @Key
    private String msgId;
    @Key("_text")
    private String text;
    @Key
    private List<Outcome> outcomes = new ArrayList<Outcome>();

    /**
     * 
     * @return
     *     The msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 
     * @param msgId
     *     The msg_id
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public GetIntentViaTextResponse withMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

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

    public GetIntentViaTextResponse withText(String Text) {
        this.text = Text;
        return this;
    }

    /**
     * 
     * @return
     *     The outcomes
     */
    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * 
     * @param outcomes
     *     The outcomes
     */
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public GetIntentViaTextResponse withOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
        return this;
    }

}
