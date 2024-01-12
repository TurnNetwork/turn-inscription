
package com.turn.inscription.param.evidence;

import com.turn.inscription.utils.HexUtil;
import lombok.Data;

@Data
public class ValidateNode {

    private int index;
    private String address;
    private String nodeId;
    public void setNodeId(String nodeId){
        this.nodeId= HexUtil.prefix(nodeId);
    }
    private String blsPubKey;

}