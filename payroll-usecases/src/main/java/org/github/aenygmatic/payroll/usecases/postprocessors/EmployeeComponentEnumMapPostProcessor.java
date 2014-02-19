/*
 * Copyright 2014 Balazs Berkes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.github.aenygmatic.payroll.usecases.postprocessors;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.github.aenygmatic.payroll.domain.EmployeeType;
import org.github.aenygmatic.payroll.domain.annotations.EmployeeComponent;
import org.github.aenygmatic.payroll.usecases.hire.HireEmployee;

@Component
public class EmployeeComponentEnumMapPostProcessor extends EnumMapGeneratingBeanPostProcessor<EmployeeComponent, EmployeeType> {

    @Override
    protected Class<EmployeeComponent> annotationType() {
        return EmployeeComponent.class;
    }

    @Override
    protected List<Class<?>> interfaceTypes() {
        return Arrays.<Class<?>>asList(HireEmployee.class);
    }

    @Override
    protected Map<EmployeeType, Object> newEnumMap() {
        return new EnumMap<>(EmployeeType.class);
    }

    @Override
    protected void addToMap(Map<EmployeeType, Object> proxyMap, Object bean) {
        EmployeeType value = bean.getClass().getAnnotation(annotationType()).value();
        proxyMap.put(value, bean);
    }

    @Override
    protected String generateName(Class<?> interfaceType) {
        String s = EmployeeType.class.getSimpleName() + interfaceType.getSimpleName() + "Proxy";
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

}
