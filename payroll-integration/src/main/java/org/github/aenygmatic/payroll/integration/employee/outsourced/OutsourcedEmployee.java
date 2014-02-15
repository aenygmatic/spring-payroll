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
import org.github.aenygmatic.payroll.domain.PayType;

public class OutsourcedEmployee implements Employee {

    private String name;
    private String position;
    private String outsourcingCompany;
    private PayType payType;

    public OutsourcedEmployee(String name, String position, PayType payType, String outsourcingCompany) {
        this.name = name;
        this.position = position;
        this.outsourcingCompany = outsourcingCompany;
        this.payType = payType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public EmployeeType type() {
        return EmployeeType.OUTSOURED;
    }

    @Override
    public PayType payType() {
        return payType;
    }

    public String getOutsourcingCompany() {
        return outsourcingCompany;
    }
}
