package kr.ac.mokwon.schoolbusclicker;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestReservationIntercity extends StringRequest
{
    //서버 url 설정(php파일 연동)
    final static private String URL = "https://as8794.cafe24.com/bus_clicker/ReservationIntercity.php";
    private Map<String, String> map;

    public RequestReservationIntercity(String userID, String userName, String bus, String date, int seat, String start, String end, String time, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userName", userName);
        map.put("bus", bus);
        map.put("date", date);
        map.put("seat", String.valueOf(seat));
        map.put("start", start);
        map.put("end", end);
        map.put("time", time);
    }

    public RequestReservationIntercity(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
