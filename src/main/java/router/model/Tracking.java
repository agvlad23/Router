package router.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;


public class Tracking {


    private int trackingId;

    private Date date;


    private Time startTime;


    private Time endTime;


    private String message;




    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracking tracking = (Tracking) o;
        return trackingId == tracking.trackingId && Objects.equals(date, tracking.date) && Objects.equals(startTime, tracking.startTime) && Objects.equals(endTime, tracking.endTime) && Objects.equals(message, tracking.message) ;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", message='" + message + '\'' +
                "}\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackingId, date, startTime, endTime, message);
    }
}
