/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2010
 * Institute for Geodesy and Geoinformation Science
 * Technische Universitaet Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 */
package org.citygml4j.model.citygml.core;

import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.GeometryProperty;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;

public interface ImplicitGeometry extends CoreModuleComponent, AbstractGML {
	public String getMimeType();
	public TransformationMatrix4x4 getTransformationMatrix();
	public String getLibraryObject();
	public GeometryProperty<? extends AbstractGeometry> getRelativeGMLGeometry();
	public PointProperty getReferencePoint();
	public boolean isSetMimeType();
	public boolean isSetTransformationMatrix();
	public boolean isSetLibraryObject();
	public boolean isSetRelativeGMLGeometry();
	public boolean isSetReferencePoint();
	
	public void setMimeType(String mimeType);
	public void setTransformationMatrix(TransformationMatrix4x4 transformationMatrix);
	public void setLibraryObject(String libraryObject);
	public void setRelativeGeometry(GeometryProperty<? extends AbstractGeometry> relativeGeometry);
	public void setReferencePoint(PointProperty referencePoint);
	public void unsetMimeType();
	public void unsetTransformationMatrix();
	public void unsetLibraryObject();
	public void unsetRelativeGMLGeometry();
	public void unsetReferencePoint();

}
