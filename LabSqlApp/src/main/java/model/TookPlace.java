package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TookPlace {
    private int research_id;
    private int institute_id;

    public static final String TABLE_TOOK_PLACE = "took_place";
    public static final String COLUMN_TP_RESEARCH_ID = "research_id";
    public static final String COLUMN_TP_INSTITUTE_ID = "institute_id";


    public int getResearch_id() {
        return research_id;
    }

    public void setResearch_id(int research_id) {
        this.research_id = research_id;
    }

    public int getInstitute_id() {
        return institute_id;
    }

    public void setInstitute_id(int institute_id) {
        this.institute_id = institute_id;
    }

    public static void searchTookPlace(){
            try(Statement statement= ConnectionPool.getConnection().createStatement();
                ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_TOOK_PLACE)) {
                List<TookPlace> tookPlaceList = new ArrayList<>();
                while (resultSet.next()){
                    TookPlace tookPlace = new TookPlace();
                    tookPlace.setResearch_id(resultSet.getInt(COLUMN_TP_RESEARCH_ID));
                    tookPlace.setInstitute_id(resultSet.getInt(COLUMN_TP_INSTITUTE_ID));
                    tookPlaceList.add(tookPlace);
                }
                if (tookPlaceList.isEmpty()){
                    System.out.println("No values at table Took Place");
                }else {
                    for (TookPlace myt:tookPlaceList){
                        System.out.println("institute id: "+ myt.getInstitute_id()+ ", research id: "+myt.getResearch_id());
                    }
                }

            } catch (SQLException e){
                System.out.println("Unable to select from took_place: "+ e.getMessage());
            }

    }
}
