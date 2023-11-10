package com.tas.es.obsw.utils.net.jpus.model.service200;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200;
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.LsCpu;
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.LsCpuException;

/**
 * Unit test for simple App.
 */
public class UnitTesLsCpu {

	@Test
	public void test() {
		try {
			Map<String, String> report = LsCpu.instance.getReport();

			Assert.assertNotNull("Cores per socket", report.get(PusAppData200.CORES_PER_SOCKET));
			Assert.assertNotNull("CPU family", report.get(PusAppData200.CPU_FAMILY));
			Assert.assertNotNull("CPU max frequency", report.get(PusAppData200.CPU_FREQ_MAX));
			Assert.assertNotNull("CPU min frequency", report.get(PusAppData200.CPU_FREQ_MIN));
			Assert.assertNotNull("CPU model", report.get(PusAppData200.CPU_MODEL));
			Assert.assertNotNull("Number of CPUs", report.get(PusAppData200.N_OF_CPU));
			Assert.assertNotNull("Number of sockets", report.get(PusAppData200.N_OF_SOCKETS));
			Assert.assertNotNull("Stepping", report.get(PusAppData200.STEPPING));
			Assert.assertNotNull("Thread per socket", report.get(PusAppData200.THREADS_PER_CORE));
			Assert.assertNotNull("CPU architecture", report.get(PusAppData200.CPU_ARCHITECTURE));
			Assert.assertNotNull("CPU byte order", report.get(PusAppData200.CPU_BYTE_ORDER));
			/* Assert.assertNotNull("CPU op mode", report.get(PusAppData200.CCORES_PER_SOCKET)); */

		} catch (LsCpuException e) {
			Assert.fail();
		}
	}
}
