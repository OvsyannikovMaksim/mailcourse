package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    protected final TextView mName;
    protected final IListener mListener;

    Item mItem;


    public ViewHolder(@NonNull View itemView, IListener listener) {
        super(itemView);

        mListener = listener;
        mName = itemView.findViewById(R.id.name);

        final View.OnClickListener clickListener = v -> mListener.onClicked(mItem);

        itemView.setOnClickListener(clickListener);
    }

    void bind(Item item) {

        mItem=item;
        mName.setText(mItem.name);

        switch (mItem.colour) {
            case 1:
                mName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.blue));
                break;

            case 0:
                mName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.red));
                break;
        }
    }
}
