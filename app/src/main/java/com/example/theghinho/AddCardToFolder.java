package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.theghinho.Model.Folder;

import java.util.List;

public class AddCardToFolder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_to_folder);
        receiveIntentFromHomePage();
    }

    private void receiveIntentFromHomePage() {
        Bundle bundle = getIntent().getBundleExtra("folderlist");
        List<Folder> folders = (List<Folder>) bundle.getSerializable("folder");
    }
}