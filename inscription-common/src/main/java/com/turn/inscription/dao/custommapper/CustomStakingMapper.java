package com.turn.inscription.dao.custommapper;

import com.turn.inscription.bean.CustomStaking;
import com.turn.inscription.dao.entity.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomStakingMapper {
	List<CustomStaking> selectByNodeId ( @Param("nodeId") String nodeId );

	List<CustomStaking> selectByNodeIdList ( @Param("nodeIds") List <String> nodeIds );

	String selectSumExitDelegate ();

	Integer selectCountByActive ();

    void updateNodeSettleStatis(List<Node> updateNodeList);
}
