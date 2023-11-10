package com.tas.es.obsw.utils.net.jpus.model.service200.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusAppData;
import com.tas.es.obsw.utils.net.jpus.visitor.PusVisitor;

public class PusAppData200001 extends PusAppData {

	@Override
	public void accept(PusVisitor v) throws PusException {
		v.visit(this);
	}

	@Override
	public byte[] getBytes() {
		return new byte[] {};
	}

	@Override
	public Map<String, String> getValues() throws PusException {
		return new HashMap<>();
	}

	@Override
	public void setValues(Map<String, String> values) throws PusException {
	}

	@Override
	public int getSerialSize() {
		/* 0 for service, 2 for CRC */
		return 0;
	}

	@Override
	public void parse(byte[] data) throws PusException {

		if (data != null && data.length != 0) {
			throw new PusException("Invalid data length");
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof PusAppData200001)) {
			return false;
		}

		return true;
	}

	@Override
	public List<String> getRequiredParameters() {

		return new ArrayList<>();
	}

	@Override
	public String toString() {
		return "";
	}
}
