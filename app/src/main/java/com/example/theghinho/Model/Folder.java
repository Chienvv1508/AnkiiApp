package com.example.theghinho.Model;

public class Folder {
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    private int UserId;

    public Folder() {

    }

}
