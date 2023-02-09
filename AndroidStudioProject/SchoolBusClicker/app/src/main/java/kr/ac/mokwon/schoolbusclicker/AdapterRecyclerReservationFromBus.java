package kr.ac.mokwon.schoolbusclicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerReservationFromBus extends RecyclerView.Adapter<AdapterRecyclerReservationFromBus.ViewHolder>
{
    private ArrayList<itemReservationCheckFromBus> data = null;

    public AdapterRecyclerReservationFromBus(ArrayList<itemReservationCheckFromBus> data)
    {
        this.data = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_reservation_from_bus, parent, false);
        AdapterRecyclerReservationFromBus.ViewHolder vh = new AdapterRecyclerReservationFromBus.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder : position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        itemReservationCheckFromBus item = data.get(position);

        holder.name.setText(item.getName());
        holder.start.setText(item.getStart());
        holder.end.setText(item.getEnd());
    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount()
    {
        return data.size();
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        EditText name, start, end;
        Button btn_OK;

        ViewHolder(View view)
        {
            super(view);
            name = view.findViewById(R.id.et_name);
            start = view.findViewById(R.id.et_start);
            end = view.findViewById(R.id.et_end);
        }
    }
}
