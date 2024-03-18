package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.theghinho.Model.Folder;

import java.util.List;

public class AddCardToFolder extends AppCompatActivity {
   List<Folder> folders;
   Spinner spnChonBoThe;
   EditText edtThemFontCard;
   EditText edtThemBackCard;
   Button btnThemCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_to_folder);

        bindingView();
       // spnChonBoThe.setAdapter();
        bindingAction();
        receiveIntentFromHomePage();
    }

    private void bindingAction() {
        btnThemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void bindingView() {
        spnChonBoThe = findViewById(R.id.spnChonBoThe);
        edtThemFontCard = findViewById(R.id.edtThemFontCard);
        edtThemBackCard = findViewById(R.id.edtThemBackCard);
        btnThemCard = findViewById(R.id.btnAddCardToFolder);
    }

    private void receiveIntentFromHomePage() {
        Bundle bundle = getIntent().getBundleExtra("folderlist");
        folders = (List<Folder>) bundle.getSerializable("folders");
    }
}