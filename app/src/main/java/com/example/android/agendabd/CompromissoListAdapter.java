package com.example.android.agendabd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CompromissoListAdapter extends RecyclerView.Adapter<CompromissoListAdapter.CompromissoViewHolder> {

    private OndeleteListener deleteListener;
    private OnEditListener editListener;

    class CompromissoViewHolder extends RecyclerView.ViewHolder {
        private final TextView compromissoItemView;
        private final TextView descricaoItemView;
        private final TextView dataItemView;

        ImageButton delete;
        ImageButton edit;

        private CompromissoViewHolder(View itemView) {
            super(itemView);
            compromissoItemView = itemView.findViewById(R.id.Compromisso);
            descricaoItemView = itemView.findViewById(R.id.Descricao);
            dataItemView = itemView.findViewById(R.id.Data);
            delete = itemView.findViewById(R.id.Delete);
            edit = itemView.findViewById(R.id.CompromissoEdit);
        }
    }

    private final LayoutInflater mInflater;
    private List<Compromisso> mCompromisso; // Cached copy of words

    CompromissoListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.editListener = editListener;
    }

    @Override
    public CompromissoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CompromissoViewHolder(itemView);
    }

    public void setDeleteListener(OndeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setEditListener(OnEditListener editListener) {
        this.editListener = editListener;
    }

    @Override
    public void onBindViewHolder(CompromissoViewHolder holder, final int position) {
        if (mCompromisso != null) {
            Compromisso current = mCompromisso.get(position);
            holder.compromissoItemView.setText(current.getCompromisso());
            holder.descricaoItemView.setText(current.getDescricao());
            holder.dataItemView.setText(current.getData());


        } else {
            // Covers the case of data not being ready yet.
            holder.compromissoItemView.setText("No Word");
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Compromisso comp = mCompromisso.get(position);
                if (deleteListener != null) {
                    deleteListener.deleteItem(comp.getCompromisso());
                }
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Compromisso comp = mCompromisso.get(position);

                if (editListener != null) {
                    editListener.editItem(comp);
                }
            }
        });
    }

    void setCompromissos(List<Compromisso> compromissos) {
        mCompromisso = compromissos;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCompromisso != null)
            return mCompromisso.size();
        else return 0;
    }
}
