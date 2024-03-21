package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;
import com.example.theghinho.Validation.CardValidation;


import java.util.List;

public class AddCardToFolder extends AppCompatActivity {
   List<Folder> folders;
   Spinner spnChonBoThe;
   EditText edtThemFontCard;
   EditText edtThemBackCard;
   Button btnThemCard;

   int folderId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_to_folder);
        receiveIntentFromHomePage();

        bindingView();

        bindingAction();

    }

    private void bindingAction() {
        spnChonBoThe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 folderId = folders.get(position).getFolderId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                folderId = folders.get(0).getFolderId();

            }
        });
        btnThemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card c = new Card();
                c.setFontCard(edtThemFontCard.getText().toString());
                c.setBackCard(edtThemBackCard.getText().toString());
                c.setFolderId(folderId);
                CardValidation.context = getApplicationContext();
                
                if(!CardValidation.validateName(c.getFontCard())){
                   Toast.makeText(getApplicationContext(),"Tên sai định dạng",Toast.LENGTH_SHORT).show();

                } else if (CardValidation.checkExistedCard(c.getFontCard())) {
                    Toast.makeText(getApplicationContext(),"Tên đã tồn tại",Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent();
                    CardDAO cardDAO = new CardDAO(getApplicationContext());
                    cardDAO.addCard(c);
                    Toast.makeText(getApplicationContext(),"Thêm Thẻ Thành Công",Toast.LENGTH_LONG).show();
                    setResult(200,intent);
                    finish();
                }

            }
        });
    }

    private void bindingView() {
        spnChonBoThe = findViewById(R.id.spnChonBoThe);
        edtThemFontCard = findViewById(R.id.edtThemFontCard);
        edtThemBackCard = findViewById(R.id.edtThemBackCard);
        btnThemCard = findViewById(R.id.btnAddCardToFolder);
        SpinnerAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                folders
        );
        spnChonBoThe.setAdapter(adapter);
        folderId = folders.get(0).getFolderId();
    }

    private void receiveIntentFromHomePage() {
        Bundle bundle = getIntent().getBundleExtra("folderlist");
        folders = (List<Folder>) bundle.getSerializable("folders");

    }
}