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

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public abstract class EnumMapGeneratingBeanPostProcessor<A extends Annotation, E extends Enum> implements BeanFactoryPostProcessor {

    protected abstract Class<A> annotationType();

    protected abstract List<Class<?>> interfaceTypes();

    protected abstract Map<E, Object> newEnumMap();

    protected abstract void addToMap(Map<E, Object> proxyMap, Object bean);

    protected abstract String generateName(Class<?> interfaceType);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(annotationType());
        for (Class<?> interfaceType : interfaceTypes()) {
            Map<E, Object> proxyMap = newEnumMap();
            for (Object bean : beansWithAnnotation.values()) {
                if (interfaceType.isInstance(bean)) {
                    addToMap(proxyMap, bean);
                }
            }
            beanFactory.registerSingleton(generateName(interfaceType), proxyMap);
        }
    }
}
