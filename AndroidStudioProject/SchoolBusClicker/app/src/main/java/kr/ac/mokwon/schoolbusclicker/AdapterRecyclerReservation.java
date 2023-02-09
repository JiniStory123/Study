package kr.ac.mokwon.schoolbusclicker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdapterRecyclerReservation extends RecyclerView.Adapter<AdapterRecyclerReservation.ViewHolder>
{
    StudentReservationCheckActivity main;
    int ID;
    private ArrayList<itemReservationCheck> data = null;

    public AdapterRecyclerReservation(ArrayList<itemReservationCheck> data)
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

        View view = inflater.inflate(R.layout.list_reservation, parent, false);
        AdapterRecyclerReservation.ViewHolder vh = new AdapterRecyclerReservation.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder : position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        itemReservationCheck item = data.get(position);

        holder.start.setText(item.getStart());
        holder.end.setText(item.getEnd());
        holder.bus.setText(item.getBus());
        holder.time.setText(item.getTime());
        holder.date.setText(item.getDate());
        holder.seat.setText(item.getSeat()+"");
        holder.id.setText(item.getId()+"");
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
        EditText start, end, bus, time, date, seat;
        TextView id;
        Button btn_OK;

        ViewHolder(View view)
        {
            super(view);
            start = view.findViewById(R.id.et_start);
            end = view.findViewById(R.id.et_end);
            bus = view.findViewById(R.id.et_bus);
            time = view.findViewById(R.id.et_time);
            date = view.findViewById(R.id.et_date);
            seat = view.findViewById(R.id.et_seat);
            id = view.findViewById(R.id.tv_id);
            btn_OK = view.findViewById(R.id.btn_OK);

            btn_OK.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ID = Integer.parseInt((String)id.getText());
                    Response.Listener<String> responseListener = new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            try
                            {
                                // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                                 System.out.println("hongchul" + response);
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success)
                                { // 예약 취소 성공
                                    Toast.makeText(id.getContext(), "예약을 취소하였습니다.",Toast.LENGTH_SHORT).show();
                                    Activity act = (Activity) id.getContext();
                                    act.finish();
                                 } else
                                { // 예약 취소 실패

                                 }
                            } catch (JSONException e)
                            {
                                 e.printStackTrace();
                            }
                        }

                    };
                RequestDelete loginRequest = new RequestDelete(ID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(id.getContext().getApplicationContext());
                queue.add(loginRequest);
                }
            });
        }
    }
}
