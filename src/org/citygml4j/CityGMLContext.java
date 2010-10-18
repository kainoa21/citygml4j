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
package org.citygml4j;

import javax.xml.bind.JAXBException;

import org.citygml4j.builder.jaxb.JAXBBuilder;

public class CityGMLContext {
	
	public JAXBBuilder createJAXBBuilder() throws JAXBException {
		return new JAXBBuilder();
	}
	
	public JAXBBuilder createJAXBBuilder(ClassLoader classLoader) throws JAXBException {
		return new JAXBBuilder(classLoader);
	}
	
}
