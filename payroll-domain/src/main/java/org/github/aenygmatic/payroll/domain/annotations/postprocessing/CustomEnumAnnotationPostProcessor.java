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
package org.github.aenygmatic.payroll.domain.annotations.postprocessing;

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public abstract class CustomEnumAnnotationPostProcessor<A extends Annotation> implements BeanFactoryPostProcessor {

    abstract Class<A> annotationType();

    abstract String[] enumToStringName(A component);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> map = configurableListableBeanFactory.getBeansWithAnnotation(annotationType());
        for (Object bean : map.values()) {
            registerCustomBeans(configurableListableBeanFactory, bean);
        }

    }

    private void registerCustomBeans(ConfigurableListableBeanFactory context, Object bean) {
        A component = bean.getClass().getAnnotation(annotationType());
        for (String name : enumToStringName(component)) {
            context.registerSingleton(name, bean);
        }
    }
}
