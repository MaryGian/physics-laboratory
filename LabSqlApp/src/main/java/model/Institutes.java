package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Institutes {
    private int id;
    private String name;

    public static final String TABLE_INSTITUTES = "institutes";
    public static final String COLUMN_INSTITUTE_ID = "institute_id";
    public static final String COLUMN_INSTITUTE_NAME = "institute_name";
    private static final String INSERT_INSTITUTES="INSERT INTO "+TABLE_INSTITUTES+"("+COLUMN_INSTITUTE_NAME+") "+"VALUES(?)";


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

    public static void selectInstitutes(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_INSTITUTES)) {

            List<Institutes> institutesList = new ArrayList<>();
            while (resultSet.next()){
                Institutes institute = new Institutes();
                institute.setId(resultSet.getInt(COLUMN_INSTITUTE_ID));
                institute.setName(resultSet.getString(COLUMN_INSTITUTE_NAME));
                institutesList.add(institute);
            }
            if (institutesList.isEmpty()){
                System.out.println("No values at table institutes");
            }else {
                for (Institutes myInstitutes:institutesList){
                    System.out.println("institute id: "+myInstitutes.getId()+", institute name: "+myInstitutes.getName());
                }
            }

        }catch (SQLException e){
            System.out.println("Unable to select from institutes: "+e.getMessage());

        }
    }


}
