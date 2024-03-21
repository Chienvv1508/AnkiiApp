package com.example.theghinho.Validation;

import android.content.Context;

import com.example.theghinho.DAO.FolderDAO;

public class FolderValidation extends CommonValidation {
    public static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        FolderValidation.context = context;
    }

    public static boolean checkExistedFolder(String name){
        FolderDAO dao = new FolderDAO(context);
      return   dao.checkFolderByFoldeNameExisted(name);
    }
}
