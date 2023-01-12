package kr.ac.mokwon.ch08_03_recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView textView;
    public ItemClickListener clickListener;

    public interface ItemClickListener
    {
        void OnClick(View View, int position);
    }

    public ViewHolder(View itemView)
    {
        super(itemView);
        itemView = itemView.findViewById(R.id.text);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(clickListener != null)
            clickListener.OnClick(view, getAdapterPosition());
    }

    public void setClickListener(ItemClickListener listener)
    {
        clickListener = listener;
    }
}