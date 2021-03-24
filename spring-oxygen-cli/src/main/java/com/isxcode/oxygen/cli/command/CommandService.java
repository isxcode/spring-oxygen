package com.isxcode.oxygen.cli.command;

import com.isxcode.oxygen.cli.store.LocalStorage;
import org.springframework.stereotype.Service;

import static com.isxcode.oxygen.cli.store.LocalStorage.localPath;
import static com.isxcode.oxygen.cli.store.LocalStorage.projectInfo;

/**
 * command center service
 *
 * @author ispong
 * @since 0.0.2
 */
@Service
public class CommandService {

    /**
     * check must params
     *
     * @return has edit power
     */
    public boolean canGenerateProject() {

        return LocalStorage.projectInfo.getArtifact() != null &&
            LocalStorage.projectInfo.getGroup() != null &&
            localPath != null;
    }

    /**
     * create project info
     *
     * @return projectInfo
     */
    public String printProjectInfo() {

        String mustTag = "[ *  ]";
        String emptyTag = "[    ]";
        String okTag = "[ OK ]";

        StringBuilder stringBuilder = new StringBuilder("");

        // localPath
        if (localPath == null) {
            stringBuilder.append(mustTag).append(" localPath: null \n");
        } else {
            stringBuilder.append(okTag).append(" localPath: ").append(localPath).append("\n");
        }

        // group
        if (projectInfo.getGroup() == null) {
            stringBuilder.append(mustTag).append(" group: null \n");
        } else {
            stringBuilder.append(okTag).append(" group: ").append(projectInfo.getGroup()).append("\n");
        }

        // artifact
        if (projectInfo.getArtifact() == null) {
            stringBuilder.append(mustTag).append(" artifact: null \n");
        } else {
            stringBuilder.append(okTag).append(" artifact: ").append(projectInfo.getArtifact()).append("\n");
        }

        if (projectInfo.getName() == null) {
            stringBuilder.append(emptyTag).append(" name: null \n");
        } else {
            stringBuilder.append(okTag).append(" name: ").append(projectInfo.getName()).append("\n");
        }

        if (projectInfo.getDescription() == null) {
            stringBuilder.append(emptyTag).append(" description: null \n");
        } else {
            stringBuilder.append(okTag).append(" description: ").append(projectInfo.getDescription()).append("\n");
        }

        if (projectInfo.getPackageName() == null) {
            stringBuilder.append(emptyTag).append(" packageName: null \n");
        } else {
            stringBuilder.append(okTag).append(" packageName: ").append(projectInfo.getPackageName()).append("\n");
        }

        return stringBuilder.toString();
    }

}
