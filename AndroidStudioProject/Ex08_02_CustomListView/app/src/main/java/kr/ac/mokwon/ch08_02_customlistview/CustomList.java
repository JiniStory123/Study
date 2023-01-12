package kr.ac.mokwon.ch08_02_customlistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomList extends ArrayAdapter<String>
{
    Activity context;
    Integer[] images;
    String[] values;
    String[] values2;
    String[] values3;


    public CustomList(@NonNull Activity context, Integer[] images, String[] values, String[] values2, String[] values3)
    {
        super(context, R.layout.item, values);
        this.context = context;
        this.images = images;
        this.values = values;
        this.values2 = values2;
        this.values3 = values3;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item, null, false);

        ImageView imageView = rowView.findViewById(R.id.image);
        imageView.setImageResource(images[position]);

        TextView textView = rowView.findViewById(R.id.txt);
        textView.setText(values[position]);

        TextView textView2 = rowView.findViewById(R.id.txt2);
        textView2.setText(values2[position]);

        TextView textView3 = rowView.findViewById(R.id.txt3);
        textView3.setText(values3[position]);

        return rowView;
    }
}