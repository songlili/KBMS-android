package com.cwp.kbms.Entity;

import java.sql.Date;

/**
 * Created by 曹伟鹏 on 2016/4/29.
 */
public class File {
    private int id;
    private Date timestamp;
    private String type;
    private String newName;
    private String originalName;
    private String path;

    public File() {
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", newName='" + newName + '\'' +
                ", path='" + path + '\'' +
                ", originalName='" + originalName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
