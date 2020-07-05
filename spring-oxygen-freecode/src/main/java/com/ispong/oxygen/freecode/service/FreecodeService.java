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
package com.ispong.oxygen.freecode.service;

import com.ispong.oxygen.freecode.exception.FreecodeException;
import com.ispong.oxygen.freecode.pojo.entity.FreecodeReq;
import com.ispong.oxygen.freecode.pojo.entity.TableColumnInfo;
import com.ispong.oxygen.freecode.pojo.constant.FreecodeConstants;
import com.ispong.oxygen.freecode.pojo.entity.FreecodeInfo;
import com.ispong.oxygen.freecode.pojo.properties.FreecodeProperties;
import com.ispong.oxygen.freecode.repository.FreecodeRepository;
import com.ispong.oxygen.freecode.utils.FreecodeUtils;

import java.util.List;

/**
 * 负责直接生成代码
 *
 * @author ispong
 * @since 0.0.1
 */
public class FreecodeService {

    private final FreecodeRepository freecodeRepository;

    private final FreecodeProperties freecodeProperties;

    public FreecodeService(FreecodeRepository freecodeRepository, FreecodeProperties freecodeProperties) {

        this.freecodeProperties = freecodeProperties;
        this.freecodeRepository = freecodeRepository;
    }

    /**
     * 开始生成代码
     *
     * @param tableName 请求对象
     * @since 0.0.1
     */
    public void startFreecode(String tableName) {

        // 表名
        String[] tableNameList = tableName.split(",");

        // 遍历表名生成对应的模块文件
        for (String metaTableName : tableNameList) {

            // 去除空
            metaTableName = metaTableName.trim();

            // 封装freeMarker的参数对象
            FreecodeInfo freecodeInfo = generateFreecodeInfo(metaTableName);

            // 遍历生成文件生成文件
            for (String fileType : freecodeProperties.getFileTypes()) {

                // 使用哪个模板文件
                String modulePath = FreecodeConstants.MAIN_PATH + freecodeProperties.getModulePath() + "." + FreecodeUtils.lineToHump(metaTableName).toLowerCase();
                String templateName = fileType + FreecodeConstants.FREEMARKER_FILE_SUFFIX;
                String fileName = FreecodeUtils.upperFirstCase(FreecodeUtils.lineToHump(metaTableName)) + FreecodeUtils.upperFirstCase(fileType) + FreecodeConstants.JAVA_FILE_SUFFIX;

                // 生成文件
                try {
                    FreecodeUtils.generateFile(modulePath.replace(".", "/"), fileName, templateName, freecodeInfo);
                } catch (Exception e) {
                    throw new FreecodeException("create file exception");
                }

            }
        }
    }

    /**
     * 生成freecodeInfo对象,用于传递对象
     *
     * @param tableName      表名
     * @return FreecodeInfo
     * @since 0.0.1
     */
    public FreecodeInfo generateFreecodeInfo(String tableName) {

        FreecodeInfo freecodeInfo = new FreecodeInfo();

        // 查询表的所有字段
        List<TableColumnInfo> tableColumns = freecodeRepository.getTableColumns(tableName, freecodeProperties.getIgnoreColumns());
        freecodeInfo.setTableColumns(tableColumns);

        // 封装EntityClassPackageList
        freecodeInfo.setEntityPackageList(FreecodeUtils.parseDataPackage(tableColumns));

        // module import class
        freecodeInfo.setPackageName(freecodeProperties.getModulePath() + "." + tableName.toLowerCase().replace("_", ""));

        // 读取配置文件
        freecodeInfo.setFreecodeProperties(freecodeProperties);

        // 储存数据库名称
        freecodeInfo.setTableName(FreecodeUtils.lineToHump(tableName));
        freecodeInfo.setPrimaryTableName(tableName);

        return freecodeInfo;
    }

}
