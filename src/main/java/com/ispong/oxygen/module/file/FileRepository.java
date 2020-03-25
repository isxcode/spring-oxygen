package com.ispong.oxygen.module.file;

import com.ispong.oxygen.flysql.Flysql;

public class FileRepository {

    public void saveFile(FileEntity fileEntity) {

        Flysql.insert(FileEntity.class).save(fileEntity);
    }

    public FileEntity getFile(String fileId) {

        return Flysql.select(FileEntity.class).eq("fileId", fileId).getOne();
    }

    public void deleteFile(String fileId) {

        Flysql.delete(FileEntity.class).eq("fileId", fileId).doDelete();
    }

}
