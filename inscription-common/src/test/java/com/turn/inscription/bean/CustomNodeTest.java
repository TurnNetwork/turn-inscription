package com.turn.inscription.bean;

import com.turn.inscription.bean.CustomNode.YesNoEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomNodeTest {

	private CustomNode customNode;

	@Test
	public void testCustomNode() {
		customNode = new CustomNode();
		customNode.updateWithCustomStaking(new CustomStaking());
		assertNotNull(customNode);
	}

	@Test
	public void testYesNoEnum() {
		YesNoEnum en = YesNoEnum.valueOf("YES");
		assertNotNull(en);
	}

}
