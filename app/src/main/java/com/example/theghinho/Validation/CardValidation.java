package com.example.theghinho.Validation;

import android.content.Context;

import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.DAO.FolderDAO;

public class CardValidation extends CommonValidation{
    public static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        CardValidation.context = context;
    }

    public static boolean checkExistedCard(String name){
        CardDAO dao = new CardDAO(context);
        return   dao.checkCardExisted(name);
    }
}
