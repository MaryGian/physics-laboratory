package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Coauthors {
    private int id;
    private String name;

    public static final String TABLE_CO_AUTHORS = "co_authors";
    public static final String COLUMN_COAUTHORS_ID = "cauthor_id";
    public static final String COLUMN_COAUTHORS_NAME = "cauthor_name";
    private static final String INSERT_COAUTHORS="INSERT INTO "+TABLE_CO_AUTHORS+"("+COLUMN_COAUTHORS_NAME+") "+"VALUES(?)";


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

    public static void selectCoauthors(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_CO_AUTHORS)) {
            List<Coauthors> coauthorsList = new ArrayList<>();
            while (resultSet.next()){

                Coauthors coauthors = new Coauthors();
                coauthors.setId(resultSet.getInt(COLUMN_COAUTHORS_ID));
                coauthors.setName (resultSet.getString(COLUMN_COAUTHORS_NAME));

                coauthorsList.add(coauthors);
            }

            if (coauthorsList.isEmpty()){
                System.out.println("No values at co-authors table");
            }else{
                for (Coauthors coauthor: coauthorsList){
                    System.out.println("co-author id: "+coauthor.getId()+", co-author name: "+coauthor.getName());
                }
            }
            System.out.println();
        }catch (SQLException e){
            System.out.println("Unable to select co-authors: "+ e.getMessage());
        }
    }



}
