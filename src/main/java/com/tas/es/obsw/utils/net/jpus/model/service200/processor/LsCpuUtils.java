package com.tas.es.obsw.utils.net.jpus.model.service200.processor;

import java.util.HashMap;
import java.util.Map;

public class LsCpuUtils {

	public final static LsCpuUtils instance = new LsCpuUtils();

	private Map<String, CpuArchitecture> cpuArchitectureNameMap;
	private Map<String, CpuOpMode> cpuOpModeNameMap;
	private Map<String, CpuByteOrder> cpuByteOrderNameMap;

	private LsCpuUtils() {

		cpuArchitectureNameMap = new HashMap<String, CpuArchitecture>();
		cpuOpModeNameMap = new HashMap<String, CpuOpMode>();
		cpuByteOrderNameMap = new HashMap<String, CpuByteOrder>();

		cpuArchitectureNameMap.put("x86_64", CpuArchitecture.X86_64);

		cpuOpModeNameMap.put("32-bit", CpuOpMode.M_32_BITS);
		cpuOpModeNameMap.put("64-bit", CpuOpMode.M_64_BITS);
		
		cpuByteOrderNameMap.put("Little Endian", CpuByteOrder.O_LITTLE_ENDIAN);
		cpuByteOrderNameMap.put("Big Endian", CpuByteOrder.O_LITTLE_ENDIAN);
	}

	public CpuArchitecture getCpuArchitectureByName(String architectureName) {
		return cpuArchitectureNameMap.get(architectureName);
	}
	
	public CpuOpMode getCpuOpModeByName(String opModeName) {
		return cpuOpModeNameMap.get(opModeName);
	}
	
	public CpuByteOrder getCpuByteOrderByName(String cpuByteOrderName) {
		return cpuByteOrderNameMap.get(cpuByteOrderName);
	}
}
