/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.wechatgo;

/**
 * 自动启动
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-26
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "spring.aop", name = "auto", havingValue = "true", matchIfMissing = true)
public class WechatgoAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(Advice.class)
    static class AspectJAutoProxyingConfiguration {

        @Configuration(proxyBeanMethods = false)
        @EnableAspectJAutoProxy(proxyTargetClass = false)
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "false",
                matchIfMissing = false)
        static class JdkDynamicAutoProxyConfiguration {

        }

        @Configuration(proxyBeanMethods = false)
        @EnableAspectJAutoProxy(proxyTargetClass = true)
        @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true",
                matchIfMissing = true)
        static class CglibAutoProxyConfiguration {

        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnMissingClass("org.aspectj.weaver.Advice")
    @ConditionalOnProperty(prefix = "spring.aop", name = "proxy-target-class", havingValue = "true",
            matchIfMissing = true)
    static class ClassProxyingConfiguration {

        ClassProxyingConfiguration(BeanFactory beanFactory) {
            if (beanFactory instanceof BeanDefinitionRegistry) {
                BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
                AopConfigUtils.registerAutoProxyCreatorIfNecessary(registry);
                AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
            }
        }

    }

}
