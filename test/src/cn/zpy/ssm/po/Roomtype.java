package cn.zpy.ssm.po;

import java.util.Date;

public class Roomtype {
    private Integer roomtypeid;

    private String roomtypename;

    private String roomtypedesc;

    private String roomtypeimg;

    private Integer priority;

    private Date createtime;

    private Date lastedittime;

    private Roomtype parentid;

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public String getRoomtypename() {
        return roomtypename;
    }

    public void setRoomtypename(String roomtypename) {
        this.roomtypename = roomtypename == null ? null : roomtypename.trim();
    }

    public String getRoomtypedesc() {
        return roomtypedesc;
    }

    public void setRoomtypedesc(String roomtypedesc) {
        this.roomtypedesc = roomtypedesc == null ? null : roomtypedesc.trim();
    }

    public String getRoomtypeimg() {
        return roomtypeimg;
    }

    public void setRoomtypeimg(String roomtypeimg) {
        this.roomtypeimg = roomtypeimg == null ? null : roomtypeimg.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(Date lastedittime) {
        this.lastedittime = lastedittime;
    }

    public Roomtype getParentid() {
        return parentid;
    }

    public void setParentid(Roomtype parentid) {
        this.parentid = parentid;
    }
}