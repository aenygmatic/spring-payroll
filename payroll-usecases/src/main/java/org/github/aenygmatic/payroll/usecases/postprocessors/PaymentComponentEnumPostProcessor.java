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

import org.github.aenygmatic.payroll.domain.PayType;
import org.github.aenygmatic.payroll.domain.annotations.PaymentComponent;
import org.github.aenygmatic.payroll.usecases.pay.PayEmployee;

@Component
public class PaymentComponentEnumPostProcessor extends EnumMapGeneratingBeanPostProcessor<PaymentComponent, PayType> {

    @Override
    protected Map<PayType, Object> newEnumMap() {
        return new EnumMap<>(PayType.class);
    }

    @Override
    protected Class<PaymentComponent> annotationType() {
        return PaymentComponent.class;
    }

    @Override
    protected void addToMap(Map<PayType, Object> proxyMap, Object bean) {
        PayType[] keys = bean.getClass().getAnnotation(annotationType()).value();
        for (PayType k : keys) {
            proxyMap.put(k, bean);
        }
    }

    @Override
    protected List<Class<?>> interfaceTypes() {
        return Arrays.<Class<?>>asList(PayEmployee.class);
    }

    @Override
    protected String generateName(Class<?> interfaceType) {
        String s = PayType.class.getSimpleName() + interfaceType.getSimpleName() + "Proxy";
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}
