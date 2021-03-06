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
package org.github.aenygmatic.payroll.integration.employee.outsourced;

import org.github.aenygmatic.payroll.domain.Employee;
import org.github.aenygmatic.payroll.domain.EmployeeType;
import org.github.aenygmatic.payroll.domain.annotations.EmployeeComponent;
import org.github.aenygmatic.payroll.usecases.hire.HireEmployee;

@EmployeeComponent(EmployeeType.OUTSOURED)
public class HireOutsourcedEmployee implements HireEmployee {

    @Override
    public void hire(Employee employee) {
        OutsourcedEmployee e = (OutsourcedEmployee) employee;
        System.out.println(String.format("%-25s Hire: %-10s %-15s %-15s -- %s:%s", this.getClass().getSimpleName(), e.getName(), e.getPosition(), e.getOutsourcingCompany(), e.type(), e.payType()));
    }
}
