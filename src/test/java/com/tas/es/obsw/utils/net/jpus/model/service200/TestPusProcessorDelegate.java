package com.tas.es.obsw.utils.net.jpus.model.service200;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusPacket;
import com.tas.es.obsw.utils.net.jpus.model.processing.PusProcessorDelegate;

public class TestPusProcessorDelegate implements PusProcessorDelegate {

	PusPacket packet;
	
	@Override
	public void send(PusPacket p) throws PusException {
		packet = p;
	}

	public PusPacket getPacket() {
		return packet;
	}
}
