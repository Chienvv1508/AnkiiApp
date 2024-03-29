package com.example.theghinho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theghinho.DAO.FolderDAO;
import com.example.theghinho.Validation.FolderValidation;
import com.example.theghinho.Model.Folder;

public class AddFolder extends AppCompatActivity {
    private EditText edtFolName;
    private Button btnAdd;
    private Button btnCancel;
    private FolderDAO folderDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_folder);
        bindingView();
        bindingAction();
        folderDAO = new FolderDAO(this);
    }

    private void bindingAction() {

        btnAdd.setOnClickListener(this:: BtnAddFolderOnClick);
        btnCancel.setOnClickListener(this:: BtnCancelOnClick);
    }

    private void BtnCancelOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }

    private void BtnAddFolderOnClick(View view) {
        Intent it = getIntent();
        String userName = it.getStringExtra("userName");
        Intent intent = new Intent();

        Folder fol = new Folder();
        String folName = edtFolName.getText().toString();
        fol.setFolderName(folName);
        FolderValidation.setContext(getApplicationContext());
        if(!FolderValidation.validateName(folName)){
            Toast.makeText(this,"Tên sai định dạng",Toast.LENGTH_SHORT).show();

        } else if (FolderValidation.checkExistedFolder(folName)) {
            Toast.makeText(this,"Tên đã tồn tại",Toast.LENGTH_SHORT).show();

        }else {
            folderDAO.insertFolder(fol, userName);
            folderDAO.close();
            setResult(200,intent);
            finish();
        }




    }

    private void bindingView() {
        edtFolName = findViewById(R.id.edtFolName);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

    }
}
