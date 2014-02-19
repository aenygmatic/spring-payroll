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
package org.github.aenygmatic.payroll.usecases.hire;

import java.util.Map;

import javax.annotation.Resource;

import org.github.aenygmatic.payroll.domain.Employee;
import org.github.aenygmatic.payroll.domain.EmployeeType;
import org.github.aenygmatic.payroll.usecases.UseCaseProxy;

@UseCaseProxy
public class HireEmployeeService implements HireEmployee {

    @Resource(name = "employeeTypeHireEmployeeProxy")
    private Map<EmployeeType, HireEmployee> usecases;

    @Override
    public void hire(Employee employee) {
        HireEmployee usecase = usecases.get(employee.type());
        if (usecase != null) {
            usecase.hire(employee);
        } else {
            throw new IllegalArgumentException("Not supported employee type: " + employee.type());
        }
    }
}
