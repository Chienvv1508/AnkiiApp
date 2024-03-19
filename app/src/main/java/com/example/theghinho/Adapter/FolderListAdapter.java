package com.example.theghinho.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.DAO.LearningLogDAO;
import com.example.theghinho.ItemViewHolder.FolderItemViewHolder;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;
import com.example.theghinho.Model.LearningLog;
import com.example.theghinho.R;

import java.util.List;

public class FolderListAdapter extends RecyclerView.Adapter<FolderItemViewHolder>{

    List<Folder> folders;
    private Context context;


    public FolderListAdapter(List<Folder> folder, Context context) {
        this.folders = folder;
        this.context = context;

    }

    @NonNull
    @Override
    public FolderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item_bothe_homepage, parent, false);
        FolderItemViewHolder folder = new FolderItemViewHolder(itemView);

        return folder;
    }

    @Override
    public void onBindViewHolder(@NonNull FolderItemViewHolder holder, int position) {
       Folder f = folders.get(position);
       CardDAO cardDAO = new CardDAO(context);
       List<Card> lisCard = cardDAO.GetAllCardsByFolderId(f.getFolderId());
       LearningLogDAO learningLogDAO = new LearningLogDAO(context);
       List<LearningLog> learningLogs = learningLogDAO.getAllCardIsNotLearnedInFolder(f.getFolderId());
       holder.setData(f.getFolderName(),lisCard.size(),learningLogs.size());

    }

    @Override
    public int getItemCount() {
        return  folders.size();
    }
}
