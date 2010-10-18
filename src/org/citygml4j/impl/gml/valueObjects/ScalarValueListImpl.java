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
package org.citygml4j.impl.gml.valueObjects;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.common.base.ModelObject;
import org.citygml4j.model.common.base.ModelType;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.basicTypes.BooleanOrNullList;
import org.citygml4j.model.gml.basicTypes.CodeOrNullList;
import org.citygml4j.model.gml.basicTypes.IntegerOrNullList;
import org.citygml4j.model.gml.basicTypes.MeasureOrNullList;
import org.citygml4j.model.gml.valueObjects.ScalarValueList;

public class ScalarValueListImpl implements ScalarValueList {
	private BooleanOrNullList booleanList;
	private CodeOrNullList categoryList;
	private MeasureOrNullList quantityList;
	private IntegerOrNullList countList;
	private ModelObject parent;
	
	public ScalarValueListImpl() {
		
	}
	
	public ScalarValueListImpl(BooleanOrNullList booleanList) {
		setBooleanList(booleanList);
	}
	
	public ScalarValueListImpl(CodeOrNullList categoryList) {
		setCategoryList(categoryList);
	}
	
	public ScalarValueListImpl(MeasureOrNullList quantityList) {
		setQuantityList(quantityList);
	}
	
	public ScalarValueListImpl(IntegerOrNullList countList) {
		setCountList(countList);
	}
	
	public ModelType getModelType() {
		return ModelType.GML;
	}
	
	public GMLClass getGMLClass() {
		return GMLClass.SCALAR_VALUE_LIST;
	}

	public BooleanOrNullList getBooleanList() {
		return booleanList;
	}

	public CodeOrNullList getCategoryList() {
		return categoryList;
	}

	public MeasureOrNullList getQuantityList() {
		return quantityList;
	}

	public IntegerOrNullList getCountList() {
		return countList;
	}

	public boolean isSetBooleanList() {
		return booleanList != null;
	}

	public boolean isSetCategoryList() {
		return categoryList != null;
	}

	public boolean isSetQuantityList() {
		return quantityList != null;
	}

	public boolean isSetCountList() {
		return countList != null;
	}

	public void setBooleanList(BooleanOrNullList booleanList) {
		if (booleanList != null)
			booleanList.setParent(this);
		
		this.booleanList = booleanList;
		
		unsetCategoryList();
		unsetCountList();
		unsetQuantityList();
	}

	public void setCategoryList(CodeOrNullList categoryList) {
		if (categoryList != null)
			categoryList.setParent(this);
		
		this.categoryList = categoryList;
		
		unsetBooleanList();
		unsetCountList();
		unsetQuantityList();
	}

	public void setQuantityList(MeasureOrNullList quantityList) {
		if (quantityList != null)
			quantityList.setParent(this);
		
		this.quantityList = quantityList;
		
		unsetBooleanList();
		unsetCategoryList();
		unsetCountList();
	}

	public void setCountList(IntegerOrNullList countList) {
		if (countList != null)
			countList.setParent(this);
		
		this.countList = countList;
		
		unsetBooleanList();
		unsetCategoryList();
		unsetQuantityList();
	}

	public void unsetBooleanList() {
		if (isSetBooleanList())
			booleanList.unsetParent();
		
		booleanList = null;
	}

	public void unsetCategoryList() {
		if (categoryList != null)
			categoryList.unsetParent();
		
		categoryList = null;
	}

	public void unsetQuantityList() {
		if (quantityList != null)
			quantityList.unsetParent();
		
		quantityList = null;
	}

	public void unsetCountList() {
		if (countList != null)
			countList.unsetParent();
		
		countList = null;
	}
	
	public ModelObject getParent() {
		return parent;
	}

	public boolean isSetParent() {
		return parent != null;
	}

	public void setParent(ModelObject parent) {
		this.parent = parent;
	}

	public void unsetParent() {
		parent = null;
	}
	
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		ScalarValueList copy = (target == null) ? new ScalarValueListImpl() : (ScalarValueList)target;
		
		if (isSetBooleanList()) {
			copy.setBooleanList((BooleanOrNullList)copyBuilder.copy(booleanList));
			if (copy.getBooleanList() == booleanList)
				booleanList.setParent(this);
		}
		
		if (isSetCategoryList()) {
			copy.setCategoryList((CodeOrNullList)copyBuilder.copy(categoryList));
			if (copy.getCategoryList() == categoryList)
				categoryList.setParent(this);
		}
		
		if (isSetQuantityList()) {
			copy.setQuantityList((MeasureOrNullList)copyBuilder.copy(quantityList));
			if (copy.getQuantityList() == quantityList)
				quantityList.setParent(this);
		}
		
		if (isSetCountList()) {
			copy.setCountList((IntegerOrNullList)copyBuilder.copy(countList));
			if (copy.getCountList() == countList)
				countList.setParent(this);
		}
		
		copy.unsetParent();
		
		return copy;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new ScalarValueListImpl(), copyBuilder);
	}

}
