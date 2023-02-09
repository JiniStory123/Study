package kr.ac.mokwon.schoolbusclicker;

public class itemReservationCheck
{
    private String start;
    private String end;
    private String bus;
    private String time;
    private String date;
    private int seat;
    private int id;

    public itemReservationCheck()
    {

    }

    public itemReservationCheck(String start, String end, String bus, String time, String date, int seat, int id) {
        this.start = start;
        this.end = end;
        this.bus = bus;
        this.time = time;
        this.date = date;
        this.seat = seat;
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
