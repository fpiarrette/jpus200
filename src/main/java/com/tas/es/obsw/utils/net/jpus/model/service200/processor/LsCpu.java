package com.tas.es.obsw.utils.net.jpus.model.service200.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200;

public class LsCpu {

	public static final LsCpu instance = new LsCpu();

	static final String LSCPU_ATTR_ARCH = "Architecture";
	/* static final String LSCPU_ATTR_CPU_OP_MODES = "CPU op-mode(s)"; */
	static final String LSCPU_ATTR_BYTE_ORDER = "Byte Order";
	static final String LSCPU_ATTR_N_OF_CPUS = "CPU(s)";
	static final String LSCPU_ATTR_CPU_FAMILY = "CPU family";
	static final String LSCPU_ATTR_CPU_MODEL = "Model";
	static final String LSCPU_ATTR_THREADS_PER_CORE = "Thread(s) per core";
	static final String LSCPU_ATTR_CORES_PER_SOCKET = "Core(s) per socket";
	static final String LSCPU_ATTR_N_OF_SOCKETS = "Socket(s)";
	static final String LSCPU_ATTR_STEPPING = "Stepping";
	static final String LSCPU_ATTR_CPU_FREQ_MAX = "CPU max MHz";
	static final String LSCPU_ATTR_CPU_FREQ_MIN = "CPU min MHz";

	private LsCpu() {
	}

	public Map<String, String> getReport() throws LsCpuException {

		Map<String, String> report = new HashMap<String, String>();

		String line;

		Process p;

		try {

			p = Runtime.getRuntime().exec("lscpu");

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = br.readLine()) != null) {

				String token[] = line.split(":");

				if (token.length > 0) {

					String k = token[0].strip();
					String v = token[1].strip();

					if (k.equals(LSCPU_ATTR_ARCH)) {
						CpuArchitecture cpuArchitecture = LsCpuUtils.instance.getCpuArchitectureByName(v);
						report.put(PusAppData200.CPU_ARCHITECTURE, cpuArchitecture.name());

					} else if (k.equals(LSCPU_ATTR_BYTE_ORDER)) {
						CpuByteOrder cpuByteOrder = LsCpuUtils.instance.getCpuByteOrderByName(v);
						report.put(PusAppData200.CPU_BYTE_ORDER, cpuByteOrder.name());

					} else if (k.equals(LSCPU_ATTR_N_OF_CPUS)) {
						report.put(PusAppData200.N_OF_CPU, v);

					} else if (k.equals(LSCPU_ATTR_CPU_FAMILY)) {
						report.put(PusAppData200.CPU_FAMILY, v);

					} else if (k.equals(LSCPU_ATTR_CPU_MODEL)) {
						report.put(PusAppData200.CPU_MODEL, v);

					} else if (k.equals(LSCPU_ATTR_THREADS_PER_CORE)) {
						report.put(PusAppData200.THREADS_PER_CORE, v);

					} else if (k.equals(LSCPU_ATTR_CORES_PER_SOCKET)) {
						report.put(PusAppData200.CORES_PER_SOCKET, v);

					} else if (k.equals(LSCPU_ATTR_N_OF_SOCKETS)) {
						report.put(PusAppData200.N_OF_SOCKETS, v);

					} else if (k.equals(LSCPU_ATTR_STEPPING)) {
						report.put(PusAppData200.STEPPING, v);

					} else if (k.equals(LSCPU_ATTR_CPU_FREQ_MAX)) {
						report.put(PusAppData200.CPU_FREQ_MAX, v.replace(',', '.'));

					} else if (k.equals(LSCPU_ATTR_CPU_FREQ_MIN)) {
						report.put(PusAppData200.CPU_FREQ_MIN, v.replace(',', '.'));
					}
				}
			}

			p.waitFor();

			if (p.exitValue() != 0) {
				throw new LsCpuException("lscpu finished with exit value " + p.exitValue());
			}

			p.destroy();

		} catch (IOException e) {
			throw new LsCpuException(e);

		} catch (InterruptedException e) {
			throw new LsCpuException(e);
		}

		return report;
	}

}
