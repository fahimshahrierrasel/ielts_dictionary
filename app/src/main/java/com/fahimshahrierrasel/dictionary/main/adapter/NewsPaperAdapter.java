package com.fahimshahrierrasel.dictionary.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fahimshahrierrasel.dictionary.R;
import com.fahimshahrierrasel.dictionary.main.data.model.NewsPaper;

import java.util.List;

public class NewsPaperAdapter extends RecyclerView.Adapter<NewsPaperAdapter.ViewHolder> {
    private List<NewsPaper> newsPapers;
    private Listener listener;

    public interface Listener {
        void onItemClicked(NewsPaper newsPaper);
    }

    public NewsPaperAdapter(List<NewsPaper> newsPapers, Listener listener) {
        this.newsPapers = newsPapers;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_newspaper, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsPaper newsPaper = newsPapers.get(i);
        viewHolder.bind(newsPaper, listener);
    }

    @Override
    public int getItemCount() {
        return newsPapers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Android Views
         **/
        ImageView imageviewNewspaperLogo;
        TextView textviewNewspaperTitle;
        TextView textviewNewspaperDesc;

        /**
         * Android Views
         **/

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        /**
         * Binds XML views
         * Call this function after layout is ready.
         **/
        private void bindViews(View rootView) {
            imageviewNewspaperLogo = rootView.findViewById(R.id.imageview_newspaper_logo);
            textviewNewspaperTitle = rootView.findViewById(R.id.textview_newspaper_title);
            textviewNewspaperDesc = rootView.findViewById(R.id.textview_newspaper_desc);
        }

        private void bind(final NewsPaper item, final Listener listener) {
            imageviewNewspaperLogo.setImageDrawable(itemView.getContext().getResources().getDrawable(item.getLogo()));
            textviewNewspaperTitle.setText(item.getName());
            textviewNewspaperDesc.setText(item.getDescription());
            itemView.setOnClickListener(view -> listener.onItemClicked(item));
        }
    }
}
