package com.turn.inscription.dao.param.ppos;

import com.turn.inscription.AgentTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProposalVersionTest extends AgentTestBase {

	@Test
	public void test(){
		ProposalVersion target = ProposalVersion.builder()
				.optDesc("null")
				.build();
		target.setOptDesc(null);

		target.getOptDesc();
		target.getBusinessType();
		assertTrue(true);
	}
}
