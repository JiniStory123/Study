package kr.ac.mokwon.schoolbusclicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestValidate extends StringRequest
{
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://as8794.cafe24.com/bus_clicker/Validate.php";
    private Map<String, String> map;

    public RequestValidate(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
