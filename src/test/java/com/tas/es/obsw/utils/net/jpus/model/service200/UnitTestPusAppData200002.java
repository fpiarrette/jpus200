package com.tas.es.obsw.utils.net.jpus.model.service200;

import java.util.HashMap;
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
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.CpuArchitecture;
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.PusProcessor200001;
import com.tas.es.obsw.utils.net.jpus.utils.PusPacketCreator;

/**
 * Unit test for simple App.
 */
public class UnitTestPusAppData200002 {

	@Test
	public void testGetBytes() {

		PusAppData appData = new PusAppData200002();

		Map<String, String> values = new HashMap<String, String>();

		values.put(PusAppData200.CPU_ARCHITECTURE, CpuArchitecture.RISCV_128.name());

		try {
			appData.setValues(values);

			byte[] bytes = appData.getBytes();

			Assert.assertEquals("Data length", 1, bytes.length);
			Assert.assertEquals("CPU architecture", CpuArchitecture.RISCV_128.ordinal(), bytes[0]);

		} catch (PusException e) {
			Assert.fail();
		}
	}

	@Test
	public void testParse() {

		PusAppData appData = new PusAppData200002();

		byte bytes[] = { 22 };

		try {

			appData.parse(bytes);

			Assert.assertEquals("Data length", 1, bytes.length);
			Assert.assertEquals("CPU architecture", CpuArchitecture.RISCV_128.ordinal(), bytes[0]);

		} catch (PusException e) {
			Assert.fail();
		}
	}
}
