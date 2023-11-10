package com.tas.es.obsw.utils.net.jpus.model.service200;

import com.tas.es.obsw.utils.net.jpus.model.PusCucTime43;
import com.tas.es.obsw.utils.net.jpus.model.processing.PusTimeSource;

public class TestPusTimeSource implements PusTimeSource {

	PusCucTime43 time;

	public TestPusTimeSource() {
	}

	public void setTime(PusCucTime43 time) {
		this.time = time;
	}

	@Override
	public PusCucTime43 getTime() {
		return time;
	}

}
