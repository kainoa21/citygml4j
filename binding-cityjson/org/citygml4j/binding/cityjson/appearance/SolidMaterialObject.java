/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 * 
 * Copyright 2013-2017 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.citygml4j.binding.cityjson.appearance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolidMaterialObject extends AbstractMaterialObject {
	private List<List<Integer>> values;
	
	SolidMaterialObject() {
	}
	
	public SolidMaterialObject(String theme) {
		super(theme);
	}
	
	@Override
	public boolean isSetValues() {
		return values != null;
	}
	
	public void addValue(List<Integer> value) {
		if (values == null)
			values = new ArrayList<>();
		
		values.add(value);
	}
	
	@Override
	public void addNullValue() {
		addValue(null);
	}

	public List<List<Integer>> getValues() {
		return values;
	}

	public void setValues(List<List<Integer>> values) {
		this.values = values;
	}
	
	@Override
	public int getNumValues() {
		return values != null ? values.size() : 0;
	}
	
	@Override
	public List<Integer> flatValues() {
		return values != null ? values.stream()
				.flatMap(a -> a != null ? a.stream() : Stream.of((Integer)null)).collect(Collectors.toList()) : null;
	}
	
	@Override
	public void unsetValues() {
		values = null;
	}
	
}
