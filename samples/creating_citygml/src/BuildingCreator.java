import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.citygml4j.CityGMLContext;
import org.citygml4j.builder.jaxb.JAXBBuilder;
import org.citygml4j.commons.gmlid.DefaultGMLIdManager;
import org.citygml4j.commons.gmlid.GMLIdManager;
import org.citygml4j.factory.CityGMLFactory;
import org.citygml4j.factory.GMLFactory;
import org.citygml4j.factory.geometry.GMLGeometryFactory;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.building.BoundarySurface;
import org.citygml4j.model.citygml.building.BoundarySurfaceProperty;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.gml.CompositeSurface;
import org.citygml4j.model.gml.Polygon;
import org.citygml4j.model.gml.Solid;
import org.citygml4j.model.gml.SurfaceProperty;
import org.citygml4j.model.module.citygml.CityGMLVersion;
import org.citygml4j.xml.io.CityGMLOutputFactory;
import org.citygml4j.xml.io.writer.CityGMLWriter;


public class BuildingCreator {
	private CityGMLFactory citygml;
	private GMLFactory gml;

	public static void main(String[] args) throws Exception {
		new BuildingCreator().doMain();
	}

	public void doMain() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("[HH:mm:ss] "); 

		System.out.println(df.format(new Date()) + "setting up citygml4j context and JAXB builder");
		CityGMLContext ctx = new CityGMLContext();
		JAXBBuilder builder = ctx.createJAXBBuilder();

		System.out.println(df.format(new Date()) + "creating LOD2 building as citygml4j in-memory object tree");
		GMLGeometryFactory geom = new GMLGeometryFactory();
		citygml = new CityGMLFactory();
		gml = new GMLFactory();

		GMLIdManager gmlIdManager = DefaultGMLIdManager.getInstance();

		Building building = citygml.createBuilding();

		Polygon ground = geom.createLinearPolygon(new double[] {0,0,0, 0,12,0, 6,12,0, 6,0,0, 0,0,0}, 3);
		Polygon wall_1 = geom.createLinearPolygon(new double[] {6,0,0, 6,12,0, 6,12,6, 6,0,6, 6,0,0}, 3);
		Polygon wall_2 = geom.createLinearPolygon(new double[] {0,0,0, 0,0,6, 0,12,6, 0,12,0, 0,0,0}, 3);
		Polygon wall_3 = geom.createLinearPolygon(new double[] {0,0,0, 6,0,0, 6,0,6, 3,0,9, 0,0,6, 0,0,0}, 3);
		Polygon wall_4 = geom.createLinearPolygon(new double[] {6,12,0, 0,12,0, 0,12,6, 3,12,9, 6,12,6, 6,12,0}, 3);
		Polygon roof_1 = geom.createLinearPolygon(new double[] {6,0,6, 6,12,6, 3,12,9, 3,0,9, 6,0,6}, 3);
		Polygon roof_2 = geom.createLinearPolygon(new double[] {0,0,6, 3,0,9, 3,12,9, 0,12,6, 0,0,6}, 3);

		ground.setId(gmlIdManager.generateGmlId());
		wall_1.setId(gmlIdManager.generateGmlId());
		wall_2.setId(gmlIdManager.generateGmlId());
		wall_3.setId(gmlIdManager.generateGmlId());
		wall_4.setId(gmlIdManager.generateGmlId());
		roof_1.setId(gmlIdManager.generateGmlId());
		roof_2.setId(gmlIdManager.generateGmlId());

		// lod2 solid
		List<SurfaceProperty> surfaceMember = new ArrayList<SurfaceProperty>();
		surfaceMember.add(gml.createSurfaceProperty('#' + ground.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + wall_1.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + wall_2.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + wall_3.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + wall_4.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + roof_1.getId()));
		surfaceMember.add(gml.createSurfaceProperty('#' + roof_2.getId()));

		CompositeSurface compositeSurface = gml.createCompositeSurface();
		compositeSurface.setSurfaceMember(surfaceMember);		
		Solid solid = gml.createSolid();
		solid.setExterior(gml.createSurfaceProperty(compositeSurface));

		building.setLod2Solid(gml.createSolidProperty(solid));

		// thematic boundary surfaces
		List<BoundarySurfaceProperty> boundedBy = new ArrayList<BoundarySurfaceProperty>();
		boundedBy.add(createBoundarySurface(CityGMLClass.GROUNDSURFACE, ground));
		boundedBy.add(createBoundarySurface(CityGMLClass.WALLSURFACE, wall_1));
		boundedBy.add(createBoundarySurface(CityGMLClass.WALLSURFACE, wall_2));
		boundedBy.add(createBoundarySurface(CityGMLClass.WALLSURFACE, wall_3));
		boundedBy.add(createBoundarySurface(CityGMLClass.WALLSURFACE, wall_4));
		boundedBy.add(createBoundarySurface(CityGMLClass.ROOFSURFACE, roof_1));
		boundedBy.add(createBoundarySurface(CityGMLClass.ROOFSURFACE, roof_2));		
		building.setBoundedBySurface(boundedBy);

		CityModel cityModel = citygml.createCityModel();
		cityModel.setBoundedBy(building.calcBoundedBy(false));
		cityModel.addCityObjectMember(citygml.createCityObjectMember(building));

		System.out.println(df.format(new Date()) + "writing citygml4j object tree");
		CityGMLOutputFactory out = builder.createCityGMLOutputFactory(CityGMLVersion.v1_0_0);
		CityGMLWriter writer = out.createCityGMLWriter(new File("LOD2_Building_v100.xml"));

		writer.setPrefixes(CityGMLVersion.v1_0_0);
		writer.setSchemaLocations(CityGMLVersion.v1_0_0);
		writer.setIndentString("  ");
		writer.write(cityModel);
		writer.close();	
		
		System.out.println(df.format(new Date()) + "CityGML file LOD2_Building_v100.xml written");
		System.out.println(df.format(new Date()) + "sample citygml4j application successfully finished");
	}

	private BoundarySurfaceProperty createBoundarySurface(CityGMLClass type, Polygon geometry) {
		BoundarySurface boundarySurface = null;

		switch (type) {
		case WALLSURFACE:
			boundarySurface = citygml.createWallSurface();
			break;
		case ROOFSURFACE:
			boundarySurface = citygml.createRoofSurface();
			break;
		case GROUNDSURFACE:
			boundarySurface = citygml.createGroundSurface();
			break;
		}

		if (boundarySurface != null) {
			boundarySurface.setLod2MultiSurface(gml.createMultiSurfaceProperty(gml.createMultiSurface(geometry)));
			return citygml.createBoundarySurfaceProperty(boundarySurface);
		}

		return null;
	}

}