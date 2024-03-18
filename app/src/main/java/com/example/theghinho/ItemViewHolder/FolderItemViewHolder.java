package com.example.theghinho.ItemViewHolder;

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

    public TextView getTxtFolder() {
        return txtFolder;
    }

    public void setTxtFolder(TextView txtFolder) {
        this.txtFolder = txtFolder;
    }

    public TextView getTxtTongSoTu() {
        return txtTongSoTu;
    }

    public void setTxtTongSoTu(TextView txtTongSoTu) {
        this.txtTongSoTu = txtTongSoTu;
    }

    public TextView getTxtTuCanHoc() {
        return txtTuCanHoc;
    }

    public void setTxtTuCanHoc(TextView txtTuCanHoc) {
        this.txtTuCanHoc = txtTuCanHoc;
    }

    public Button getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(Button btnAction) {
        this.btnAction = btnAction;
    }

    public FolderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        bindingView();
        bindingAction();
    }

    private void bindingAction() {
        Toast.makeText(itemView.getContext(),txtFolder.getText().toString(),Toast.LENGTH_LONG).show();
    }

    private void bindingView() {
        txtFolder = itemView.findViewById(R.id.txtFolderR);

    }

}
