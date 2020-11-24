/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.core.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 封装常用属性值
 *
 * @author ispong
 * @since 0.0.1
 */
@Data
public class FieldBody {

    private Method readMethod;

    private Method writeMethod;

    private Field field;

    private String className;
}
