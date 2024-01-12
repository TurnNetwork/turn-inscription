package com.turn.inscription.dao.custommapper;

import com.turn.inscription.dao.entity.Address;
import com.turn.inscription.dao.entity.NetworkStat;
import com.turn.inscription.task.bean.AddressStatistics;
import com.turn.inscription.task.bean.NetworkStatistics;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticBusinessMapper {

    /**
     * Change of address data
     *
     * @param list:
     * @return: void
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    void addressChange(List<Address> list);

    /**
     * Add addresses in batches
     *
     * @param list:
     * @return: int
     */
    int batchInsert(List<Address> list);

    /**
     *Statistics changes
     *
     * @param param
     */
    @Transactional(rollbackFor = {Exception.class, Error.class})
    void networkChange(NetworkStat param);

    /**
     * Get network
     *
     * @return
     */
    NetworkStatistics getNetworkStatisticsFromNode();

    /**
     * Get the number of addresses
     *
     * @return
     */
    Integer getNetworkStatisticsFromAddress();

    /**
     * Get the proposals in the voting
     *
     * @return
     */
    Integer getNetworkStatisticsFromProposal();

    /**
     * Get the total number of proposals
     */
    Integer getProposalQty();

    List<AddressStatistics> getAddressStatisticsFromStaking(@Param("list") List<String> list);

    List<AddressStatistics> getAddressStatisticsFromDelegation(@Param("list") List<String> list);

    @Transactional(rollbackFor = {Exception.class, Error.class})
    int batchUpdateFromTask(@Param("list") List<Address> list);


}