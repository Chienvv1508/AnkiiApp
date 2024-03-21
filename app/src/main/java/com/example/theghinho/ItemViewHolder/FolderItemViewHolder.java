package com.example.theghinho.ItemViewHolder;





import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.FolderDetail;
import com.example.theghinho.Learn;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;
import com.example.theghinho.Model.LearningLog;
import com.example.theghinho.R;

import java.io.Serializable;
import java.util.List;


public class FolderItemViewHolder extends RecyclerView.ViewHolder {
    private TextView txtFolder;
    private  TextView txtTongSoTu;
    private  TextView txtTuCanHoc;
    private Button btnAction, btnChiTiet;
    private Folder folder;
    private List<Card> cards;

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<LearningLog> getCacTuCanHoc() {
        return cacTuCanHoc;
    }

    public void setCacTuCanHoc(List<LearningLog> cacTuCanHoc) {
        this.cacTuCanHoc = cacTuCanHoc;
    }

    private List<LearningLog> cacTuCanHoc;

private Context context;

    public FolderItemViewHolder(@NonNull View itemView , Context context) {
        super(itemView);
        this.context = context;
        bindingView();
        bindingAction();
    }

    private void bindingAction() {
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cacTuCanHoc.size() == 0){
                    Toast.makeText(context,"Chưa có thẻ cần học",Toast.LENGTH_SHORT).show();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("learningCards", (Serializable) cacTuCanHoc);
                    Intent i = new Intent(context, Learn.class);
                    i.putExtra("cacTuCanHoc",bundle);
                    startActivity(context,i,null);
                }

            }
        });
        btnChiTiet.setOnClickListener(this::onChiTietClick);
    }

    private void onChiTietClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("folder", (Serializable) folder);
        bundle.putSerializable("cards", (Serializable) cards);
        Intent i = new Intent(context, FolderDetail.class);
        i.putExtra("folderDetail",bundle);

        startActivity(context,i,null);

    }

    private void bindingView() {

        txtFolder = itemView.findViewById(R.id.txtFolderR);
        txtTongSoTu =itemView.findViewById(R.id.txtTongSoTuR);
        txtTuCanHoc = itemView.findViewById(R.id.txtTuCanHocR);
        btnAction = itemView.findViewById(R.id.btnActionR);
        btnChiTiet = itemView.findViewById(R.id.btnChiTiet);


    }

    public void setData(String fol, int tongSoTu, int soTuCanHoc){
        txtFolder.setText(fol);
        String tongSoTu1 = "Tổng số từ: " + tongSoTu;
        String soTuCanHoc1 = "Số từ cần học " + soTuCanHoc;
        txtTongSoTu.setText(tongSoTu1);
        txtTongSoTu.setTextColor(Color.BLUE);
        txtTuCanHoc.setText(soTuCanHoc1);
        txtTuCanHoc.setTextColor(Color.RED);
    }

}
