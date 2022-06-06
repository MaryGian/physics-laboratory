package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Teach {
    private int member_id;
    private int course_id;
    private int hours;

    public static final String TABLE_TEACH = "teach";
    public static final String COLUMN_TEACH_COURSE_ID = "course_id";
    public static final String COLUMN_TEACH_MEMBER_ID = "member_id";
    public static final String COLUMN_TEACH_HOURS = "hours_for_each";


    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMember_id() {
        return member_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getHours() {
        return hours;
    }

    public static void selectTeach(){

            try(Statement statement= ConnectionPool.getConnection().createStatement();
                ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_TEACH)) {
                List<Teach> teachList = new ArrayList<>();
                while (resultSet.next()){
                    Teach teach = new Teach();
                    teach.setCourse_id(resultSet.getInt(COLUMN_TEACH_COURSE_ID));
                    teach.setMember_id(resultSet.getInt(COLUMN_TEACH_MEMBER_ID));
                    teach.setHours(resultSet.getInt(COLUMN_TEACH_HOURS));
                    teachList.add(teach);
                }
                if (teachList.isEmpty()){
                    System.out.println("No values at table Teach");
                }else {
                    for (Teach myT:teachList){
                        System.out.println("course id: "+myT.getCourse_id()+", member id: "+myT.getMember_id()+ " teaching hours: "+myT.getHours());
                    }
                    System.out.println();
                }

            } catch (SQLException e){
                System.out.println("Unable to select from roles: "+ e.getMessage());

            }
    }
}
