package com.turn.inscription.analyzer.ppos;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.bean.*;
import com.turn.inscription.cache.NodeCache;
import com.turn.inscription.config.BlockChainConfig;
import com.turn.inscription.dao.custommapper.SlashBusinessMapper;
import com.turn.inscription.dao.entity.Node;
import com.turn.inscription.dao.mapper.NodeMapper;
import com.turn.inscription.dao.mapper.SlashMapper;
import com.turn.inscription.elasticsearch.dto.Block;
import com.turn.inscription.elasticsearch.dto.Transaction;
import com.turn.inscription.service.ppos.StakeEpochService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @Description:
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ReportConverterTest extends AgentTestBase {

    @Mock
    private BlockChainConfig chainConfig;

    @Mock
    private SlashBusinessMapper slashBusinessMapper;

    @Mock
    private NodeCache nodeCache;

    @Mock
    private StakeEpochService stakeEpochService;

    @Mock
    private NodeMapper nodeMapper;

    @InjectMocks
    @Spy
    private ReportAnalyzer target;

    @Mock
    private SlashMapper slashMapper;

    @Before
    public void setup() throws Exception {
        NodeItem nodeItem = NodeItem.builder()
                                    .nodeId("0x77fffc999d9f9403b65009f1eb27bae65774e2d8ea36f7b20a89f82642a5067557430e6edfe5320bb81c3666a19cf4a5172d6533117d7ebcd0f2c82055499050")
                                    .nodeName("integration-node1")
                                    .stakingBlockNum(new BigInteger("88602"))
                                    .build();
        when(nodeCache.getNode(anyString())).thenReturn(nodeItem);
        when(chainConfig.getDuplicateSignSlashRate()).thenReturn(blockChainConfig.getDuplicateSignSlashRate());
        when(chainConfig.getConsensusPeriodBlockCount()).thenReturn(blockChainConfig.getConsensusPeriodBlockCount());
        when(chainConfig.getDuplicateSignRewardRate()).thenReturn(blockChainConfig.getDuplicateSignRewardRate());
        when(stakeEpochService.getUnStakeEndBlock(anyString(), any(BigInteger.class), anyBoolean())).thenReturn(BigInteger.TEN);
        when(stakeEpochService.getUnStakeFreeDuration()).thenReturn(BigInteger.TEN);
        when(slashMapper.insert(any())).thenReturn(1);
    }

    @Test
    public void convert() {
        Block block = blockList.get(0);
        EpochMessage epochMessage = EpochMessage.newInstance();
        epochMessage.setSettleEpochRound(BigInteger.TEN);
        CollectionEvent collectionEvent = new CollectionEvent();
        collectionEvent.setBlock(block);
        collectionEvent.setEpochMessage(epochMessage);
        Transaction tx = new Transaction();
        for (CollectionTransaction collectionTransaction : transactionList) {
            if (collectionTransaction.getTypeEnum().equals(Transaction.TypeEnum.REPORT)) {
                tx = collectionTransaction;
            }
        }
        Node node = new Node();
        node.setStakingLocked(BigDecimal.TEN);
        node.setStatus(CustomStaking.StatusEnum.CANDIDATE.getCode());
        when(nodeMapper.selectByPrimaryKey(any())).thenReturn(node);
        target.analyze(collectionEvent, tx);
        node.setStakingLocked(BigDecimal.ZERO);
        node.setStakingReduction(BigDecimal.TEN);
        target.analyze(collectionEvent, tx);
    }

}