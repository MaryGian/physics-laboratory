package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Announcements {
    private int id;
    private String title;
    private int member_id;
    private int course_id;
    private String details;
    private String date;

    public static final String TABLE_ANNOUNCEMENTS = "announcements";
    public static final String COLUMN_ANNOUNCE_ID = "an_id";
    public static final String COLUMN_ANNOUNCE_COURSE_ID = "course_id";
    public static final String COLUMN_ANNOUNCE_MEMBER_ID = "member_id";
    public static final String COLUMN_ANNOUNCE_TITLE = "title";
    public static final String COLUMN_ANNOUNCE_DETAIL = "detail";
    public static final String COLUMN_ANNOUNCE_DATE ="date_of_announcement";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static void selectAnnouncements(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_ANNOUNCEMENTS)) {
            List<Announcements> announcementsList = new ArrayList<>();
            while (resultSet.next()){

                Announcements announcement = new Announcements();
                announcement.setId(resultSet.getInt(COLUMN_ANNOUNCE_ID));
                announcement.setCourse_id(resultSet.getInt(COLUMN_ANNOUNCE_COURSE_ID));
                announcement.setMember_id(resultSet.getInt(COLUMN_ANNOUNCE_MEMBER_ID));
                announcement.setTitle(resultSet.getString(COLUMN_ANNOUNCE_TITLE));
                announcement.setDetails(resultSet.getString(COLUMN_ANNOUNCE_DETAIL));
                announcement.setDate(resultSet.getString(COLUMN_ANNOUNCE_DATE));

                announcementsList.add(announcement);
            }
            if (!announcementsList.isEmpty()) {
                for (Announcements announcements : announcementsList){
                    System.out.println("id:"+announcements.getId()+" course id:"+announcements.getCourse_id()+" member id: "+announcements.getMember_id()+
                            " title:"+announcements.getTitle()+" details:"+announcements.getDetails()+" date:"+announcements.getDate());
                }

            }
        }catch (SQLException e){
            System.out.println("Unable to select announcements: "+ e.getMessage());
        }

    }


}
