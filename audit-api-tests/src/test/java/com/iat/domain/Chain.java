package com.iat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.ChainActions;
import com.iat.utils.HelpFunctions;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Chain {

    private String id;
    private String chainName;
    private String parent;
    private String groupId;
    private String partner_api_key;
    private String partner_short_name;

    public Chain() {
    }

    public Chain(@JsonProperty("id") String id,
                 @JsonProperty("chainName") String chainName,
                 @JsonProperty("parent") String parent,
                 @JsonProperty("groupId") String groupId,
                 @JsonProperty("partner_short_name") String partner_short_name,
                 @JsonProperty("partner_api_key") String partner_api_key) {
        this.id = id;
        this.chainName = chainName;
        this.parent = parent;
        this.groupId = groupId;
        this.partner_short_name = partner_short_name;
        this.partner_api_key = partner_api_key;

        generateDefaultData();
    }

    public void generateDefaultData() {
        if (id.equals("TODAYS") || id.equals("PREMIER") || id.equals("NISA")) {
            ChainActions chainActions = new ChainActions();
            ObjectMapper mapper = new ObjectMapper();
            HelpFunctions helpFunctions = new HelpFunctions();

            mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
            Chain chain = new Chain();

            try {
                chain = mapper.readValue(chainActions.getChainDetailsById(helpFunctions.getChainIdForPartnerShortName(id)), Chain.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            setId(chain.getId());
            setChainName(chain.getChainName());
            setGroupId(chain.getGroupId());
            setParent(chain.getParent());
            setPartner_api_key(chain.getPartner_api_key());
            setPartner_short_name(chain.getPartner_short_name());
        }
    }

    @Override
    public String toString() {
        return ("{" +
                (id == null ? "" : "\"id\": " + id + ", ") +
                (chainName == null ? "" : "\"chainName\": \"" + chainName + "\", ") +
                (parent == null ? "" : "\"parent\": \"" + parent + "\", ") +
                (groupId == null ? "" : "\"groupId\": \"" + groupId + "\", ") +
                (partner_short_name == null ? "" : "\"partner_short_name\": \"" + partner_short_name + "\", ") +
                (partner_api_key == null ? "" : "\"partner_api_key\": \"" + partner_api_key + "\", ") +
                '}').replace(", }", "}");

    }
}