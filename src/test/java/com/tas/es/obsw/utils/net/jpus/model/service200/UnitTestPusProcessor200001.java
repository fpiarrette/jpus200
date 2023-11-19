package com.tas.es.obsw.utils.net.jpus.model.service200;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusAppData;
import com.tas.es.obsw.utils.net.jpus.model.PusCucTime43;
import com.tas.es.obsw.utils.net.jpus.model.PusPacket;
import com.tas.es.obsw.utils.net.jpus.model.PusProcessingStage;
import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200;
import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200001;
import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200002;
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.PusProcessor200001;
import com.tas.es.obsw.utils.net.jpus.utils.PusPacketCreator;

/**
 * Unit test for simple App.
 */
public class UnitTestPusProcessor200001 {

	@Test
	public void testSupports() {

		PusProcessor200001 p = new PusProcessor200001();

		try {

			PusPacket tc = PusPacketCreator.tc().apid(1000).st(200).sst(1).sid(100).appData(new PusAppData200001())
					.build();

			Assert.assertTrue("Processor result", p.supports(PusProcessingStage.EXECUTION, tc));

		} catch (PusException e) {
			Assert.fail();
		}

	}

	@Test
	public void testExecute() {

		PusProcessor200001 p = new PusProcessor200001();

		TestPusProcessorDelegate delegate = new TestPusProcessorDelegate();
		TestPusTimeSource timeSource = new TestPusTimeSource();

		try {

			PusPacket tc = PusPacketCreator.tc().apid(1000).st(200).sst(1).sid(100).appData(new PusAppData200001())
					.build();

			timeSource.setTime(new PusCucTime43(0, 0));

			boolean result = p.execute(PusProcessingStage.EXECUTION, tc, delegate, timeSource);

			Assert.assertTrue("Processor result", result);

			PusPacket tm = delegate.getPacket();

			Assert.assertTrue("Packet is TM", !tm.getHeader().isTc());
			Assert.assertEquals("Service type", 200, tm.getDataField().getHeader().getServiceType().getValue());
			Assert.assertEquals("Service sub type", 2, tm.getDataField().getHeader().getServiceSubType().getValue());

			PusAppData pusAppData = tm.getDataField().getContent().getSpecificApplicationData();

			Assert.assertTrue("PusAppData instance class", pusAppData instanceof PusAppData200002);

			PusAppData200002 pusAppData200002 = (PusAppData200002) pusAppData;

			Map<String, String> values = pusAppData200002.getValues();

			Assert.assertTrue("PusAppData contains " + PusAppData200.CPU_ARCHITECTURE,
					values.containsKey(PusAppData200.CPU_ARCHITECTURE));

		} catch (PusException e) {
			Assert.fail();
		}
	}
}
