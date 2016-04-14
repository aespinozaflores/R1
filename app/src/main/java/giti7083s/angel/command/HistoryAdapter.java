package giti7083s.angel.command;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angel on 11/04/16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    ArrayList<String> historyStrings;

    public HistoryAdapter(ArrayList<String> historyStrings) {
        this.historyStrings = historyStrings;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        //TODO: display name ofaction with its id or number
        String history = historyStrings.get(position);
        holder.tv_history.setText(history);
    }

    @Override
    public int getItemCount() {
        return historyStrings.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_history)
        TextView tv_history;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
