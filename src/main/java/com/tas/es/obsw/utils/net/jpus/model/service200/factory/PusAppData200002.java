package com.tas.es.obsw.utils.net.jpus.model.service200.factory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusAppData;
import com.tas.es.obsw.utils.net.jpus.model.service200.processor.CpuArchitecture;
import com.tas.es.obsw.utils.net.jpus.visitor.PusVisitor;

public class PusAppData200002 extends PusAppData {

	CpuArchitecture cpuArchitecture;

	@Override
	public void accept(PusVisitor v) throws PusException {
		v.visit(this);
	}

	@Override
	public byte[] getBytes() {

		byte[] buffer = new byte[1];

		buffer[0] = (byte) ((cpuArchitecture.ordinal() >> 0) & 0xFF);

		return buffer;
	}

	@Override
	public int getSerialSize() {
		/* 1 */
		return 1;
	}

	@Override
	public void parse(byte[] data) throws PusException {

		if (data != null && data.length < 1) {
			throw new PusException("Invalid data length");
		}

		Integer cpuArchitectureOrdinal = (data[0] << 0) & 0xFF;

		cpuArchitecture = CpuArchitecture.values()[cpuArchitectureOrdinal];
	}

	@Override
	public Map<String, String> getValues() throws PusException {
		HashMap<String, String> values = new HashMap<>();

		values.put(PusAppData200.CPU_ARCHITECTURE, cpuArchitecture.name());

		return values;
	}

	@Override
	public void setValues(Map<String, String> values) throws PusException {

		/* First validate that all parameters are inside */
		validate(values);
		/* Convert them */

		cpuArchitecture = CpuArchitecture.valueOf(values.get(PusAppData200.CPU_ARCHITECTURE));

	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof PusAppData200002)) {
			return false;
		}

		PusAppData200002 other = (PusAppData200002) obj;

		return cpuArchitecture.equals(other.cpuArchitecture);
	}

	@Override
	public List<String> getRequiredParameters() {

		return Arrays.asList(new String[] { PusAppData200.CPU_ARCHITECTURE });
	}

	@Override
	public String toString() {
		return cpuArchitecture.name();
	}
}
