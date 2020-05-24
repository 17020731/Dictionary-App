package com.hiepdt.dicitonaryapp.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hiepdt.dicitonaryapp.R;
import com.hiepdt.dicitonaryapp.models.APP;
import com.hiepdt.dicitonaryapp.models.Word;
import com.hiepdt.dicitonaryapp.search.result.ResultActivity;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Word> mListHistory;

    public SearchAdapter(Context mContext, ArrayList<Word> mListHis) {
        this.mContext = mContext;
        this.mListHistory = mListHis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Word word = mListHistory.get(position);
        holder.text.setText(word.getKey());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ResultActivity.class);
                intent.putExtra("word", word.getKey());
                if (APP.LANG_DICTION.equalsIgnoreCase("en")) {
                    int pos = APP.mListWordEng.indexOf(word.getKey());
                    intent.putExtra("meaning", APP.mListDictionEng.get(pos).getMeaning());
                } else {
                    int pos = APP.mListWordVie.indexOf(word.getKey());
                    intent.putExtra("meaning", APP.mListDictionVie.get(pos).getMeaning());
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
