package org.citygml4j.builder.cityjson.unmarshal.citygml.transportation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.citygml4j.binding.cityjson.CityJSON;
import org.citygml4j.binding.cityjson.feature.AbstractCityObjectType;
import org.citygml4j.binding.cityjson.feature.AbstractTransportationComplexType;
import org.citygml4j.binding.cityjson.feature.RailwayType;
import org.citygml4j.binding.cityjson.feature.RoadType;
import org.citygml4j.binding.cityjson.feature.TransportSquareType;
import org.citygml4j.binding.cityjson.feature.TransportationComplexAttributes;
import org.citygml4j.binding.cityjson.geometry.AbstractGeometryType;
import org.citygml4j.binding.cityjson.geometry.AbstractSemanticsObject;
import org.citygml4j.binding.cityjson.geometry.AbstractSurfaceCollectionType;
import org.citygml4j.binding.cityjson.geometry.SemanticsType;
import org.citygml4j.builder.cityjson.unmarshal.CityJSONUnmarshaller;
import org.citygml4j.builder.cityjson.unmarshal.citygml.CityGMLUnmarshaller;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.generics.StringAttribute;
import org.citygml4j.model.citygml.transportation.AbstractTransportationObject;
import org.citygml4j.model.citygml.transportation.AuxiliaryTrafficArea;
import org.citygml4j.model.citygml.transportation.AuxiliaryTrafficAreaProperty;
import org.citygml4j.model.citygml.transportation.Railway;
import org.citygml4j.model.citygml.transportation.Road;
import org.citygml4j.model.citygml.transportation.Square;
import org.citygml4j.model.citygml.transportation.TrafficArea;
import org.citygml4j.model.citygml.transportation.TrafficAreaProperty;
import org.citygml4j.model.citygml.transportation.TransportationComplex;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurface;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;
import org.citygml4j.util.gmlid.DefaultGMLIdManager;
import org.citygml4j.util.mapper.BiFunctionTypeMapper;

public class TransportationUnmarshaller {
	private final CityJSONUnmarshaller json;
	private final CityGMLUnmarshaller citygml;
	private final BiFunctionTypeMapper<CityJSON, AbstractCityObject> typeMapper;

	public TransportationUnmarshaller(CityGMLUnmarshaller citygml) {
		this.citygml = citygml;
		json = citygml.getCityJSONUnmarshaller();

		typeMapper = BiFunctionTypeMapper.<CityJSON, AbstractCityObject>create()
				.with(RoadType.class, this::unmarshalRoad)
				.with(RailwayType.class, this::unmarshalRailway)
				.with(TransportSquareType.class, this::unmarshalTransportSquare);
	}

	public AbstractCityObject unmarshal(AbstractCityObjectType src, CityJSON cityJSON) {
		return typeMapper.apply(src, cityJSON);
	}

	public void unmarshalSemantics(AbstractSemanticsObject src, Map<Integer, List<AbstractSurface>> surfaces, Number lod, AbstractCityObject parent) {
		if (!(parent instanceof TransportationComplex))
			return;
		
		for (int i = 0; i < src.getNumSurfaces(); i++) {
			SemanticsType semanticsType = src.getSurfaces().get(i);
			if (semanticsType == null)
				continue;

			List<AbstractSurface> tmp = surfaces.get(i);
			if (tmp == null || tmp.isEmpty())
				continue;

			AbstractTransportationObject traffixArea = null;
			switch (semanticsType.getType()) {
			case TRAFFIC_AREA:
				traffixArea = unmarshalTrafficArea(semanticsType, tmp, lod);
				break;
			case AUXILIARY_TRAFFIC_AREA:
				traffixArea = unmarshalAuxiliaryTrafficArea(semanticsType, tmp, lod);
				break;
			default:
				continue;
			}

			if (traffixArea instanceof TrafficArea)
				((TransportationComplex)parent).addTrafficArea(new TrafficAreaProperty((TrafficArea)traffixArea));
			else if (traffixArea instanceof AuxiliaryTrafficArea)
				((TransportationComplex)parent).addAuxiliaryTrafficArea(new AuxiliaryTrafficAreaProperty((AuxiliaryTrafficArea)traffixArea));
		}
	}

	public void unmarshalTransportationComplex(AbstractTransportationComplexType src, TransportationComplex dest) {
		citygml.getCoreUnmarshaller().unmarshalAbstractCityObject(src, dest);

		if (src.isSetAttributes()) {
			TransportationComplexAttributes attributes = src.getAttributes();

			if (attributes.isSetClazz())
				dest.setClazz(new Code(attributes.getClazz()));

			if (attributes.isSetFunction())
				dest.addFunction(new Code(attributes.getFunction()));

			if (attributes.isSetUsage())
				dest.addUsage(new Code(attributes.getUsage()));

			if (attributes.isSetSurfaceMaterials()) {
				StringAttribute surfaceMaterial = new StringAttribute();
				surfaceMaterial.setName("surfaceMaterial");
				surfaceMaterial.setValue(attributes.getSurfaceMaterials().stream().collect(Collectors.joining(" ")));
				dest.addGenericAttribute(surfaceMaterial);
			}
		}

		for (AbstractGeometryType geometryType : src.getGeometry()) {
			if (geometryType instanceof AbstractSurfaceCollectionType) {
				AbstractSurfaceCollectionType surfaceType = (AbstractSurfaceCollectionType)geometryType;
				MultiSurface multiSurface = json.getGMLUnmarshaller().unmarshalMultiSurface(surfaceType, dest);

				if (multiSurface != null) {
					int lod = geometryType.getLod().intValue();
					switch (lod) {
					case 1:
						dest.setLod1MultiSurface(new MultiSurfaceProperty(multiSurface));
						break;
					case 2:
						dest.setLod2MultiSurface(new MultiSurfaceProperty(multiSurface));
						break;
					case 3:
						dest.setLod3MultiSurface(new MultiSurfaceProperty(multiSurface));
						break;
					}
				}
			}	
		}
	}

	public Road unmarshalRoad(RoadType src, CityJSON cityJSON) {
		Road dest = new Road();
		unmarshalTransportationComplex(src, dest);

		return dest;
	}

	public Railway unmarshalRailway(RailwayType src, CityJSON cityJSON) {
		Railway dest = new Railway();
		unmarshalTransportationComplex(src, dest);

		return dest;
	}

	public Square unmarshalTransportSquare(TransportSquareType src, CityJSON cityJSON) {
		Square dest = new Square();
		unmarshalTransportationComplex(src, dest);

		return dest;
	}

	public void unmarshalTrafficArea(SemanticsType src, TrafficArea dest, List<AbstractSurface> surfaces, Number lod) {
		dest.setId(DefaultGMLIdManager.getInstance().generateUUID());
		
		if (src.isSetProperties()) {
			Object attribute = src.getProperties().get("surfaceMaterial");
			if (attribute instanceof String) {
				dest.setSurfaceMaterial(new Code((String)attribute));
				src.getProperties().remove("surfaceMaterial");
			}
			
			attribute = src.getProperties().get("class");
			if (attribute instanceof String) {
				dest.setClazz(new Code((String)attribute));
				src.getProperties().remove("class");
			}
			
			attribute = src.getProperties().get("function");
			if (attribute instanceof String) {
				dest.addFunction(new Code((String)attribute));
				src.getProperties().remove("function");
			}
			
			attribute = src.getProperties().get("usage");
			if (attribute instanceof String) {
				dest.addFunction(new Code((String)attribute));
				src.getProperties().remove("usage");
			}
			
			citygml.getGenericsUnmarshaller().unmarshalSemanticsAttributes(src.getProperties(), dest);
		}
		
		MultiSurface multiSurface = new MultiSurface();
		for (AbstractSurface surface : surfaces)
			multiSurface.addSurfaceMember(new SurfaceProperty(surface));

		switch (lod.intValue()) {
		case 2:
			dest.setLod2MultiSurface(new MultiSurfaceProperty(multiSurface));
			break;
		case 3:
			dest.setLod3MultiSurface(new MultiSurfaceProperty(multiSurface));
			break;
		}
	}

	public TrafficArea unmarshalTrafficArea(SemanticsType src, List<AbstractSurface> surfaces, Number lod) {
		TrafficArea dest = new TrafficArea();
		unmarshalTrafficArea(src, dest, surfaces, lod);

		return dest;
	}

	public void unmarshalAuxiliaryTrafficArea(SemanticsType src, AuxiliaryTrafficArea dest, List<AbstractSurface> surfaces, Number lod) {
		dest.setId(DefaultGMLIdManager.getInstance().generateUUID());
		
		if (src.isSetProperties()) {
			Object attribute = src.getProperties().get("surfaceMaterial");
			if (attribute instanceof String) {
				dest.setSurfaceMaterial(new Code((String)attribute));
				src.getProperties().remove("surfaceMaterial");
			}
			
			attribute = src.getProperties().get("class");
			if (attribute instanceof String) {
				dest.setClazz(new Code((String)attribute));
				src.getProperties().remove("class");
			}
			
			attribute = src.getProperties().get("function");
			if (attribute instanceof String) {
				dest.addFunction(new Code((String)attribute));
				src.getProperties().remove("function");
			}
			
			attribute = src.getProperties().get("usage");
			if (attribute instanceof String) {
				dest.addFunction(new Code((String)attribute));
				src.getProperties().remove("usage");
			}
			
			citygml.getGenericsUnmarshaller().unmarshalSemanticsAttributes(src.getProperties(), dest);
		}
		
		MultiSurface multiSurface = new MultiSurface();
		for (AbstractSurface surface : surfaces)
			multiSurface.addSurfaceMember(new SurfaceProperty(surface));

		switch (lod.intValue()) {
		case 2:
			dest.setLod2MultiSurface(new MultiSurfaceProperty(multiSurface));
			break;
		case 3:
			dest.setLod3MultiSurface(new MultiSurfaceProperty(multiSurface));
			break;
		}
	}

	public AuxiliaryTrafficArea unmarshalAuxiliaryTrafficArea(SemanticsType src, List<AbstractSurface> surfaces, Number lod) {
		AuxiliaryTrafficArea dest = new AuxiliaryTrafficArea();
		unmarshalAuxiliaryTrafficArea(src, dest, surfaces, lod);
		
		return dest;
	}
}