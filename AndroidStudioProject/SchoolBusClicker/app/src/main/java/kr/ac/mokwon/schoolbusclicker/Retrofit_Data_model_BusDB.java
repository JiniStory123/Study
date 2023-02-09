package kr.ac.mokwon.schoolbusclicker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Retrofit_Data_model_BusDB
{
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("bus")
    @Expose
    private String bus;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("seat")
    @Expose
    private int seat;

    @SerializedName("start")
    @Expose
    private String start;

    @SerializedName("end")
    @Expose
    private String end;

    @SerializedName("time")
    @Expose
    private String time;

    public int getId()
    {
        return id;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getBus()
    {
        return bus;
    }

    public String getDate()
    {
        return date;
    }

    public int getSeat()
    {
        return seat;
    }

    public String getStart()
    {
        return start;
    }

    public String getEnd()
    {
        return end;
    }

    public String getTime()
    {
        return time;
    }
}
