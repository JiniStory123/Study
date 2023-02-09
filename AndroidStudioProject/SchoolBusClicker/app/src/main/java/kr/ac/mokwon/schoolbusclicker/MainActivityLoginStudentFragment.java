package kr.ac.mokwon.schoolbusclicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityLoginStudentFragment extends Fragment
{
    MainActivity main;
    public MainActivityLoginStudentFragment()
    {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        main = (MainActivity)context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        main = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_main_login_student_fragment, container, false);

        EditText et_id = view.findViewById(R.id.userID);
        EditText et_password = view.findViewById(R.id.userPassword);
        Button btn_login = view.findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String userPass = et_password.getText().toString();

                // EditText에 빈값이 없는지 확인한다
                if(userID.equals("") || userPass.equals(""))
                {
                    Toast.makeText(getActivity(),"빈값없이 모두 입력해야 합니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

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
                            { // 로그인에 성공한 경우
                                et_id.setText("");
                                et_password.setText("");
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                String userName = jsonObject.getString("userName");
                                String userAge = jsonObject.getString("userAge");
                                String userType = jsonObject.getString("userType");
                                Intent intent = new Intent(getActivity(), StudentMainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPass", userPass);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userAge", userAge);
                                intent.putExtra("userType", userType);
                                startActivity(intent);
                                Toast.makeText(getActivity(),"로그인 성공",Toast.LENGTH_SHORT).show();
                            } else
                            { // 로그인에 실패한 경우
                                Toast.makeText(getActivity(),"아이디 혹은 비밀번호가 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RequestLogin2 loginRequest = new RequestLogin2(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(loginRequest);
            }
        });

        return view;
    }
}
