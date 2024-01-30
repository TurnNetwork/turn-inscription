package com.turn.inscription.handler;

import com.turn.inscription.AgentTestBase;
import com.turn.inscription.bean.GasEstimateEvent;
import com.turn.inscription.dao.custommapper.EpochBusinessMapper;
import com.turn.inscription.dao.entity.GasEstimate;
import com.turn.inscription.dao.mapper.GasEstimateLogMapper;
import com.turn.inscription.exception.BeanCreateOrUpdateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GasEstimateEventHandlerTest extends AgentTestBase {

	@Mock
	private GasEstimateLogMapper gasEstimateLogMapper;
	@Mock
	private EpochBusinessMapper epochBusinessMapper;
	@InjectMocks
	@Spy
	private GasEstimateEventHandler target;
	
	@Before
    public void setup() {
    }
	
	 @Test
	 public void test() throws InterruptedException, ExecutionException, BeanCreateOrUpdateException, IOException {
		 GasEstimateEvent gasEstimateEvent = new GasEstimateEvent();
		 List<GasEstimate> estimateList = new ArrayList<>();
		 GasEstimate gasEstimate = new GasEstimate();
		 gasEstimate.setAddr("1");
		 estimateList.add(gasEstimate);
		 gasEstimateEvent.setEstimateList(estimateList);
		 gasEstimateEvent.setSeq(1l);
		 target.onEvent(gasEstimateEvent, 1, false);
		 gasEstimateEvent.getEstimateList();
		 gasEstimateEvent.getSeq();
		 ReflectionTestUtils.setField(target, "epochBusinessMapper", null);
		 target.onEvent(gasEstimateEvent, 1, false);
	 }
	
}
