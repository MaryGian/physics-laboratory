package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoWrite {

    private int coauthor_id;
    private int publication_id;

    public static final String TABLE_CO_WRITE = "co_write";
    public static final String COLUMN_COWRITE_COAUTHOR_ID = "cauthor_id";
    public static final String COLUMN_COWRITE_PUB_ID = "pub_id";



    public int getCoauthor_id() {
        return coauthor_id;
    }

    public void setCoauthor_id(int coauthor_id) {
        this.coauthor_id = coauthor_id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public static void selectCowrite(){
        try(Statement statement = ConnectionPool.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+TABLE_CO_WRITE)){
            List<CoWrite> coWriteList =  new ArrayList<>();
            while (resultSet.next()){
                CoWrite coWrite = new CoWrite();
                coWrite.setCoauthor_id(resultSet.getInt(COLUMN_COWRITE_COAUTHOR_ID));
                coWrite.setPublication_id(resultSet.getInt(COLUMN_COWRITE_PUB_ID));
                coWriteList.add(coWrite);
            }
            if (coWriteList.isEmpty()){
                System.out.println("The table co_write is empty");
            }else {
                for (CoWrite mycoWrite: coWriteList){
                    System.out.println("co-author id: "+mycoWrite.getCoauthor_id()+", publications id: "+ mycoWrite.getPublication_id());
                }
            }


        }catch (SQLException e){
            System.out.println("Unable to select from CoWrite: "+e.getMessage());

        }
    }


}
