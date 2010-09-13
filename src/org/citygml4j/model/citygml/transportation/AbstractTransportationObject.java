package org.citygml4j.model.citygml.transportation;

import java.util.List;

import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.AbstractCityObject;

public interface AbstractTransportationObject extends TransportationModuleComponent, AbstractCityObject {
	public List<ADEComponent> getGenericApplicationPropertyOfTransportationObject();
	public boolean isSetGenericApplicationPropertyOfTransportationObject();
	
	public void addGenericApplicationPropertyOfTransportationObject(ADEComponent ade);
	public void setGenericApplicationPropertyOfTransportationObject(List<ADEComponent> ade);
	public void unsetGenericApplicationPropertyOfTransportationObject();
	public boolean unsetGenericApplicationPropertyOfTransportationObject(ADEComponent ade);
}