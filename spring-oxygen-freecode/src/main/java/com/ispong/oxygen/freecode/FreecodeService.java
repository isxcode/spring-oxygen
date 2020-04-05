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
package com.ispong.oxygen.freecode;

import java.util.ArrayList;

/**
 * 负责直接生成代码
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeService {

    private final FreecodeRepository freecodeRepository;

    public FreecodeService(FreecodeRepository freecodeRepository) {

        this.freecodeRepository = freecodeRepository;
    }

    public void startFreecode(String tableName) {

        generateFile(generateFreecodeInfo(tableName));
    }

    /**
     * 生成freecodeInfo对象,用于传递对象
     *
     * @param tableName 表名
     * @return FreecodeInfo
     * @since 0.0.1
     */
    public FreecodeInfo generateFreecodeInfo(String tableName) {

        FreecodeInfo freecodeInfo = new FreecodeInfo();

        freecodeRepository.getTableColumns("primary","log", new ArrayList<>());
        freecodeRepository.getTableInfo("log");
        return null;
    }

    /**
     * 生成文件
     *
     * @param freecodeInfo freemarker的对象
     * @since 0.0.1
     */
    public void generateFile(FreecodeInfo freecodeInfo) {

        // 解析文件对象
//        FreecodeUtils.generateFile();
    }

}
