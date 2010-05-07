package org.citygml4j.impl.jaxb.citygml.relief._0_4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.citygml4j.impl.jaxb.ModelMapper;
import org.citygml4j.impl.jaxb.gml._3_1_1.MultiPointPropertyImpl;
import org.citygml4j.jaxb.citygml._0_4.MassPointReliefType;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.relief.MassPointRelief;
import org.citygml4j.model.gml.MultiPoint;
import org.citygml4j.model.gml.MultiPointProperty;

public class MassPointReliefImpl extends ReliefComponentImpl implements MassPointRelief {
	private MassPointReliefType massPointReliefType;

	public MassPointReliefImpl() {
		this(new MassPointReliefType());
	}

	public MassPointReliefImpl(MassPointReliefType massPointReliefType) {
		super(massPointReliefType);
		this.massPointReliefType = massPointReliefType;
	}

	@Override
	public CityGMLClass getCityGMLClass() {
		return CityGMLClass.MASSPOINTRELIEF;
	}

	@Override
	public MassPointReliefType getJAXBObject() {
		return massPointReliefType;
	}

	public MultiPointProperty getReliefPoints() {
		if (massPointReliefType.isSetReliefPoints())
			return new MultiPointPropertyImpl(massPointReliefType.getReliefPoints());

		return null;
	}

	public void setReliefPoints(MultiPointProperty reliefPoints) {
		massPointReliefType.setReliefPoints(((MultiPointPropertyImpl)reliefPoints).getJAXBObject());
	}

	@Override
	public void calcBoundedBy() {
		super.calcBoundedBy();

		if (isSetReliefPoints()) {
			MultiPoint multiPoint = getReliefPoints().getMultiPoint();

			if (multiPoint != null)
				calcBoundedBy(multiPoint);
		}
	}

	public void addGenericApplicationPropertyOfMassPointRelief(ADEComponent adeObject) {
		JAXBElement<?> jaxbElem = ModelMapper.ADE.toJAXB(adeObject);
		if (jaxbElem != null)
			massPointReliefType.get_GenericApplicationPropertyOfMassPointRelief().add(jaxbElem);
	}

	public List<ADEComponent> getGenericApplicationPropertyOfMassPointRelief() {
		List<ADEComponent> adeList = new ArrayList<ADEComponent>();

		for (JAXBElement<?> elem : massPointReliefType.get_GenericApplicationPropertyOfMassPointRelief()) {
			if (elem.getValue() != null) {
				ADEComponent ade = ModelMapper.ADE.toADEComponent(elem.getValue(), elem.getName());
				if (ade != null)
					adeList.add(ade);
			}
		}

		return adeList;
	}

	public void setGenericApplicationPropertyOfMassPointRelief(List<ADEComponent> adeObject) {
		List<JAXBElement<?>> elemList = new ArrayList<JAXBElement<?>>();

		for (ADEComponent ade : adeObject) {
			JAXBElement<?> elem = ModelMapper.ADE.toJAXB(ade);
			if (elem != null)
				elemList.add(elem);
		}

		if (!elemList.isEmpty()) {
			massPointReliefType.unset_GenericApplicationPropertyOfMassPointRelief();
			massPointReliefType.get_GenericApplicationPropertyOfMassPointRelief().addAll(elemList);
		}
	}

	public boolean isSetGenericApplicationPropertyOfMassPointRelief() {
		return massPointReliefType.isSet_GenericApplicationPropertyOfMassPointRelief();
	}

	public boolean isSetReliefPoints() {
		return massPointReliefType.isSetReliefPoints();
	}

	public void unsetGenericApplicationPropertyOfMassPointRelief() {
		massPointReliefType.unset_GenericApplicationPropertyOfMassPointRelief();
	}

	public void unsetReliefPoints() {
		massPointReliefType.setReliefPoints(null);
	}

	public boolean unsetGenericApplicationPropertyOfMassPointRelief(ADEComponent adeObject) {
		if (massPointReliefType.isSet_GenericApplicationPropertyOfMassPointRelief()) {
			Iterator<JAXBElement<?>> iter = massPointReliefType.get_GenericApplicationPropertyOfMassPointRelief().iterator();
			while (iter.hasNext()) {
				JAXBElement<?> elem = iter.next();
				if (elem.getValue() != null && elem.getValue() != null) {
					JAXBElement<?> ade = ModelMapper.ADE.toJAXB(adeObject);
					if (ade != null && ade.getValue() != null && elem.getValue().equals(ade.getValue())) {
						iter.remove();
						return true;
					}
				}
			}				
		}

		return false;
	}

}