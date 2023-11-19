package com.tas.es.obsw.utils.net.jpus.model.service200.processor;

import java.util.Map;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusAppData;
import com.tas.es.obsw.utils.net.jpus.model.PusPacket;
import com.tas.es.obsw.utils.net.jpus.model.PusPacketDataFieldHeaderTc;
import com.tas.es.obsw.utils.net.jpus.model.PusProcessingStage;
import com.tas.es.obsw.utils.net.jpus.model.processing.PusProcessor;
import com.tas.es.obsw.utils.net.jpus.model.processing.PusProcessorDelegate;
import com.tas.es.obsw.utils.net.jpus.model.processing.PusTimeSource;
import com.tas.es.obsw.utils.net.jpus.model.service200.factory.PusAppData200002;
import com.tas.es.obsw.utils.net.jpus.utils.PusPacketCreator;

public class PusProcessor200001 implements PusProcessor {

	public PusProcessor200001() {
	}

	@Override
	public boolean supports(PusProcessingStage stage, PusPacket p) {
		if (!p.getHeader().isTc()) {
			return false;
		}

		if (!stage.equals(PusProcessingStage.EXECUTION)) {
			return false;
		}

		PusPacketDataFieldHeaderTc dataFieldHeader = (PusPacketDataFieldHeaderTc) p.getDataField().getHeader();

		return dataFieldHeader.getServiceType().getValue() == 200
				&& dataFieldHeader.getServiceSubType().getValue() == 1;
	}

	@Override
	public boolean execute(PusProcessingStage stage, PusPacket p, PusProcessorDelegate d, PusTimeSource timeSource)
			throws PusException {

		if (!p.getHeader().isTc()) {
			throw new PusException("TM PUS packet not supported");
		}

		if (!stage.equals(PusProcessingStage.EXECUTION)) {
			throw new PusException("Stage not supported");
		}

		PusPacketDataFieldHeaderTc dataFieldHeader = (PusPacketDataFieldHeaderTc) p.getDataField().getHeader();

		if (dataFieldHeader.getServiceType().getValue() != 200 || dataFieldHeader.getServiceSubType().getValue() != 1) {
			throw new PusException("PUS service not supported");
		}

		int apid = p.getHeader().getApplicationProcessId();
		int sid = ((PusPacketDataFieldHeaderTc) p.getDataField().getHeader()).getSourceId().getValue();

		try {

			Map<String, String> report = LsCpu.instance.getReport();

			PusAppData pusAppData = new PusAppData200002();

			pusAppData.setValues(report);

			PusPacket tm = PusPacketCreator.tm().apid(apid).st(200).sst(2).did(sid).time(timeSource.getTime())
					.appData(pusAppData).build();
			d.send(tm);

		} catch (LsCpuException e) {
			return false;
		}

		return true;
	}

	@Override
	public String getName() {
		return "CPU management: supported by lscpu command";
	}

}
