package com.tas.es.obsw.utils.net.jpus.model.service200.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.tas.es.obsw.utils.net.jpus.PusException;
import com.tas.es.obsw.utils.net.jpus.model.PusAppData;
import com.tas.es.obsw.utils.net.jpus.model.PusPacketDataFieldHeader;
import com.tas.es.obsw.utils.net.jpus.model.PusServicePair;
import com.tas.es.obsw.utils.net.jpus.model.PusServiceSubType;
import com.tas.es.obsw.utils.net.jpus.model.PusServiceType;
import com.tas.es.obsw.utils.net.jpus.model.ext.appdata.PusAppDataFactory;

public class PusAppDataFactory200 implements PusAppDataFactory {

	private static final PusServiceType SUPPORTED_SERVICE_TYPE = new PusServiceType(200);

	HashMap<PusServicePair, Class<? extends PusAppData>> applicationDataTc;
	HashMap<PusServicePair, Class<? extends PusAppData>> applicationDataTm;

	public PusAppDataFactory200() {

		applicationDataTc = new HashMap<PusServicePair, Class<? extends PusAppData>>();
		applicationDataTm = new HashMap<PusServicePair, Class<? extends PusAppData>>();

		populate();
	}

	protected void populate() {

		applicationDataTc.put(new PusServicePair(200, 1), PusAppData200001.class);
		applicationDataTm.put(new PusServicePair(200, 2), PusAppData200002.class);
	}

	@Override
	public String getName() {
		return "CPU management";
	}

	@Override
	public PusServiceType getSupportedService() {
		return SUPPORTED_SERVICE_TYPE;
	}

	@Override
	public List<PusServiceSubType> getAvailableSubServices() {

		Set<PusServicePair> servicePairs = applicationDataTc.keySet();
		List<PusServiceSubType> supportedServices = new ArrayList<PusServiceSubType>();

		for (PusServicePair p : servicePairs) {
			supportedServices.add(p.getServiceSubType());
		}

		servicePairs = applicationDataTm.keySet();

		for (PusServicePair p : servicePairs) {
			supportedServices.add(p.getServiceSubType());
		}

		return supportedServices;
	}

	@Override
	public List<PusServiceSubType> getAvailableSubServiceTcs() {

		Set<PusServicePair> servicePairs = applicationDataTc.keySet();
		List<PusServiceSubType> supportedServices = new ArrayList<PusServiceSubType>();

		for (PusServicePair p : servicePairs) {
			supportedServices.add(p.getServiceSubType());
		}

		return supportedServices;
	}

	@Override
	public boolean supports(PusServiceType serviceType, PusServiceSubType serviceSubType) {

		PusServicePair p = new PusServicePair(serviceType, serviceSubType);

		return applicationDataTc.containsKey(p) || applicationDataTm.containsKey(p);
	}

	@Override
	public boolean supports(PusPacketDataFieldHeader pusPacketDataFieldHeader) {
		return supports(pusPacketDataFieldHeader.getServiceType(), pusPacketDataFieldHeader.getServiceSubType());
	}

	@Override
	public PusAppData create(PusServiceSubType serviceSubType) throws PusException {

		PusServicePair p = new PusServicePair(SUPPORTED_SERVICE_TYPE, serviceSubType);

		try {

			Class<?> clazz = applicationDataTc.get(p);

			if (clazz == null) {
				clazz = applicationDataTm.get(p);
			}

			if (clazz != null) {
				return (PusAppData) clazz.newInstance();
			} else
				return null;

		} catch (InstantiationException e) {

			throw new PusException(e);

		} catch (IllegalAccessException e) {
			throw new PusException(e);
		}
	}

	@Override
	public List<String> getRequiredParameters(PusServiceSubType pusServiceSubType) throws PusException {
		return create(pusServiceSubType).getRequiredParameters();
	}
}
