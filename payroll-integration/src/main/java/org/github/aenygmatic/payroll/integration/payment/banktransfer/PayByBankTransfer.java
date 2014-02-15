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
package org.github.aenygmatic.payroll.integration.payment.banktransfer;

import org.github.aenygmatic.payroll.domain.Employee;
import org.github.aenygmatic.payroll.domain.PayType;
import org.github.aenygmatic.payroll.domain.annotations.PaymentComponent;
import org.github.aenygmatic.payroll.usecases.pay.PayEmployee;

@PaymentComponent(PayType.BANK_TRANSFER)
public class PayByBankTransfer implements PayEmployee {

    @Override
    public void pay(Employee e) {
        System.out.println(String.format("%-25s Paying: %-10s -- %s:%s", this.getClass().getSimpleName(), e.getName(), e.type(), e.payType()));
    }

}
