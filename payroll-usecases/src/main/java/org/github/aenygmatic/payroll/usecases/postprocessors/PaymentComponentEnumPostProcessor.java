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

import org.github.aenygmatic.payroll.domain.PayType;
import org.github.aenygmatic.payroll.domain.annotations.PaymentComponent;
import org.github.aenygmatic.payroll.usecases.pay.PayEmployee;

@Deprecated
public class PaymentComponentEnumPostProcessor extends EnumMapGeneratingBeanPostProcessor<PaymentComponent, PayEmployee> {

    private final Map<PayType, PayEmployee> beans = new EnumMap<>(PayType.class);

    @Override
    Class<PaymentComponent> annotationType() {
        return PaymentComponent.class;
    }

    @Override
    Class<PayEmployee> interfaceType() {
        return PayEmployee.class;
    }

    @Override
    void addToMap(PayEmployee bean) {
        PayType[] keys = bean.getClass().getAnnotation(annotationType()).value();
        for (PayType k : keys) {
            beans.put(k, bean);
        }
    }

    @Override
    String getName() {
        return "payTypeToPayEmployee";
    }

    @Override
    Object getMap() {
        return beans;
    }

}
