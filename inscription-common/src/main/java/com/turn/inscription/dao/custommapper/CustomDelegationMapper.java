package com.turn.inscription.dao.custommapper;

import com.github.pagehelper.Page;
import com.turn.inscription.bean.CustomDelegation;
import com.turn.inscription.bean.DelegationAddress;
import com.turn.inscription.bean.DelegationStaking;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomDelegationMapper {

    List<CustomDelegation> selectByNodeId(@Param("nodeId") String nodeId);

    List<CustomDelegation> selectByNodeIdList(@Param("nodeIds") List<String> nodeIds);

    Page<DelegationStaking> selectStakingByNodeId(@Param("nodeId") String nodeId);

    Page<DelegationAddress> selectAddressByAddr(@Param("delegateAddr") String delegateAddr);

}
