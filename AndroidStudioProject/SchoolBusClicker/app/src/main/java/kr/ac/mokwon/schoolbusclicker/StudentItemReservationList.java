package kr.ac.mokwon.schoolbusclicker;

public class StudentItemReservationList
{
    private String start, end, bus, time;
    int seat;

    public StudentItemReservationList()
    {}

    public StudentItemReservationList(String start, String end, String bus, String time, int seat)
    {
        this.start = start;
        this.end = end;
        this.bus = bus;
        this.time = time;
        this.seat = seat;
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
