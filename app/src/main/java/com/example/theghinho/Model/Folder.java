package com.example.theghinho.Model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Folder implements Serializable {
    private int FolderId;
    private String FolderName;

    public int getFolderId() {
        return FolderId;
    }

    public void setFolderId(int folderId) {
        FolderId = folderId;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    private String UserName;

    public Folder() {

    }

    @NonNull
    @Override
    public String toString() {
        return FolderName;
    }
}
