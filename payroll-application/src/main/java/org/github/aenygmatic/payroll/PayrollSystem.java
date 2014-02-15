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
package org.github.aenygmatic.payroll;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import org.github.aenygmatic.payroll.domain.Employee;
import org.github.aenygmatic.payroll.domain.PayType;
import org.github.aenygmatic.payroll.integration.employee.internal.InternalEmployee;
import org.github.aenygmatic.payroll.integration.employee.outsourced.OutsourcedEmployee;
import org.github.aenygmatic.payroll.usecases.Service;
import org.github.aenygmatic.payroll.usecases.hire.HireEmployee;
import org.github.aenygmatic.payroll.usecases.pay.PayEmployee;

@Component
public class PayrollSystem {

    @Service
    @Autowired
    private HireEmployee hireEmployeeService;
    @Service
    @Autowired
    private PayEmployee payEmployeeService;

    private List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void addEmployees() {
        employees.add(new OutsourcedEmployee("Joe", "Manager", PayType.BANK_TRANSFER, "IndianSource"));
        employees.add(new OutsourcedEmployee("Meggie", "Developer", PayType.CASH, "IndianSource"));
        employees.add(new OutsourcedEmployee("Jack", "Tester", PayType.PAYCHECK, "IndianSource"));
        employees.add(new InternalEmployee("Jaimie", "King Slayer", PayType.CASH));
        employees.add(new InternalEmployee("Emma", "CEO", PayType.BANK_TRANSFER));
        employees.add(new InternalEmployee("Mike", "Nothing", PayType.PAYCHECK));
    }

    public void hireAndPayAll() {
        System.out.println("\n------------------------------------- Hire employees -------------------------------------");
        for (Employee e : employees) {
            hireEmployeeService.hire(e);
        }
        System.out.println("\n------------------------------------- Pay employees --------------------------------------");
        for (Employee e : employees) {
            payEmployeeService.pay(e);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.github.aenygmatic.payroll");
        PayrollSystem system = context.getBean(PayrollSystem.class);
        system.hireAndPayAll();
    }
}
