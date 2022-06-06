package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Courses {
    private int id;
    private String name;
    private int ects;
    private boolean undergraduate;

    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSES_ID = "course_id";
    public static final String COLUMN_COURSES_NAME = "course_name";
    public static final String COLUMN_COURSES_ECTS = "ects";
    public static final String COLUMN_COURSES_UNDERGRADUATE = "undergraduate";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public boolean isUndergraduate() {
        return undergraduate;
    }

    public void setUndergraduate(boolean undergraduate) {
        this.undergraduate = undergraduate;
    }

    public static void selectCourses() {
        String level;
        try (Statement statement = ConnectionPool.getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM "+ TABLE_COURSES)){
            List<Courses> coursesList= new ArrayList<>();
            while (resultSet.next()){
                Courses course =  new Courses();
                course.setId(resultSet.getInt(COLUMN_COURSES_ID));
                course.setEcts(resultSet.getInt(COLUMN_COURSES_ECTS));
                course.setName(resultSet.getString(COLUMN_COURSES_NAME));
                course.setUndergraduate(resultSet.getBoolean(COLUMN_COURSES_UNDERGRADUATE));
                coursesList.add(course);
            }
            if (coursesList.isEmpty()){
                System.out.println("No values at the table courses");
            }else{
                for (Courses myCourses: coursesList){
                    if(myCourses.isUndergraduate()){
                        level="undergraduate students";
                    }else {
                        level="master students";
                    }
                    System.out.println("course id: "+myCourses.getId()+", course name: "+myCourses.getName()+", ects: "+myCourses.getEcts()+", is for: "+level);
                }
            }
            System.out.println();
        }catch (SQLException e){
            System.out.println("Unable to select courses: "+e.getMessage());

        }
    }


}
