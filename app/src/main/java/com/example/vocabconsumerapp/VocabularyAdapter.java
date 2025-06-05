package com.example.vocabconsumerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {
    private List<Vocabulary> vocabList;

    public static class Vocabulary {
        public String english;
        public String vietnamese;

        public Vocabulary(String english, String vietnamese) {
            this.english = english;
            this.vietnamese = vietnamese;
        }
    }

    public VocabularyAdapter(List<Vocabulary> vocabList) {
        this.vocabList = vocabList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vocabulary vocab = vocabList.get(position);
        holder.text1.setText(vocab.english);
        holder.text2.setText(vocab.vietnamese);
    }

    @Override
    public int getItemCount() {
        return vocabList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
