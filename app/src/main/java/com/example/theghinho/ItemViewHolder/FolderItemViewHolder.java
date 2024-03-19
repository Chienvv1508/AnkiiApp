package com.example.theghinho.ItemViewHolder;





import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.R;


public class FolderItemViewHolder extends RecyclerView.ViewHolder {
    private TextView txtFolder;
    private  TextView txtTongSoTu;
    private  TextView txtTuCanHoc;
    private Button btnAction;


    public FolderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    private void bindingAction() {
        //Toast.makeText(itemView.getContext(),txtFolder.getText().toString(),Toast.LENGTH_LONG).show();
    }

    private void bindingView() {

        txtFolder = itemView.findViewById(R.id.txtFolderR);
        txtTongSoTu =itemView.findViewById(R.id.txtTongSoTuR);
        txtTuCanHoc = itemView.findViewById(R.id.txtTuCanHocR);

    }
    public void setData(String fol, int tongSoTu, int soTuCanHoc){
        txtFolder.setText(fol);
        String tongSoTu1 = "Số từ cần học: " + tongSoTu;
        String soTuCanHoc1 = "Tổng số từ: " + soTuCanHoc;
        txtTongSoTu.setText(tongSoTu1);
        txtTongSoTu.setTextColor(Color.BLUE);
        txtTuCanHoc.setText(soTuCanHoc1);
        txtTuCanHoc.setTextColor(Color.RED);
    }

}
