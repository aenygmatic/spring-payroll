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

import java.util.EnumMap;
import java.util.Map;

import org.github.aenygmatic.payroll.domain.EmployeeType;
import org.github.aenygmatic.payroll.domain.annotations.EmployeeComponent;
import org.github.aenygmatic.payroll.usecases.hire.HireEmployee;

@Deprecated
public class EmployeeComponentEnumMapPostProcessor extends EnumMapGeneratingBeanPostProcessor<EmployeeComponent, HireEmployee> {

    private final Map<EmployeeType, HireEmployee> beans = new EnumMap<>(EmployeeType.class);

    @Override
    Class<EmployeeComponent> annotationType() {
        return EmployeeComponent.class;
    }

    @Override
    Class<HireEmployee> interfaceType() {
        return HireEmployee.class;
    }

    @Override
    void addToMap(HireEmployee bean) {
        EmployeeType value = bean.getClass().getAnnotation(annotationType()).value();
        beans.put(value, bean);
    }

    @Override
    String getName() {
        return "employeeTypeToHireEmployee";
    }

    @Override
    Object getMap() {
        return beans;
    }

}
