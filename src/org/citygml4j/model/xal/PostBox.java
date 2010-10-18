/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2010
 * Institute for Geodesy and Geoinformation Science
 * Technische Universitšt Berlin, Germany
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
package org.citygml4j.model.xal;

import java.util.List;

import org.citygml4j.model.common.child.Child;
import org.citygml4j.model.common.copy.Copyable;
import org.citygml4j.model.common.visitor.XALFunctor;
import org.citygml4j.model.common.visitor.XALVisitor;

public interface PostBox extends XAL, Child, Copyable {
	public List<AddressLine> getAddressLine();
	public PostBoxNumber getPostBoxNumber();
	public PostBoxNumberPrefix getPostBoxNumberPrefix();
	public PostBoxNumberSuffix getPostBoxNumberSuffix();
	public PostBoxNumberExtension getPostBoxNumberExtension();
	public Firm getFirm();
	public PostalCode getPostalCode();
	public String getType();
	public String getIndicator();
	public boolean isSetAddressLine();
	public boolean isSetPostBoxNumber();
	public boolean isSetPostBoxNumberPrefix();
	public boolean isSetPostBoxNumberSuffix();
	public boolean isSetPostBoxNumberExtension();
	public boolean isSetFirm();
	public boolean isSetPostalCode();
	public boolean isSetType();
	public boolean isSetIndicator();
	
	public void addAddressLine(AddressLine addressLine);
	public void setAddressLine(List<AddressLine> addressLine);
	public void setPostBoxNumber(PostBoxNumber postBoxNumber);
	public void setPostBoxNumberPrefix(PostBoxNumberPrefix postBoxNumberPrefix);
	public void setPostBoxNumberSuffix(PostBoxNumberSuffix postBoxNumberSuffix);
	public void setPostBoxNumberExtension(PostBoxNumberExtension postBoxNumberExtension);
	public void setFirm(Firm firm);
	public void setPostalCode(PostalCode postalCode);
	public void setType(String type);
	public void setIndicator(String indicator);
	public void unsetAddressLine();
	public boolean unsetAddressLine(AddressLine addressLine);
	public void unsetPostBoxNumber();
	public void unsetPostBoxNumberPrefix();
	public void unsetPostBoxNumberSuffix();
	public void unsetPostBoxNumberExtension();
	public void unsetFirm();
	public void unsetPostalCode();
	public void unsetType();
	public void unsetIndicator();
	
	public void visit(XALVisitor visitor);
	public <T> T visit(XALFunctor<T> visitor);
}
