package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResearchDetails {
    private int id;
    private String name;
    private boolean ongoing;

    public static final String TABLE_RESEARCH_DETAILS = "research_details";
    public static final String COLUMN_RD_PROGRAM_NAME = "program_name";
    public static final String COLUMN_RD_ID = "research_id";
    public static final String COLUMN_RD_ONGOING = "ongoing";

    private static final String INSERT_RESEARCH_DETAILS="INSERT INTO "+TABLE_RESEARCH_DETAILS+"("+COLUMN_RD_PROGRAM_NAME+","+COLUMN_RD_ONGOING+") VALUES(?,?,?,?,?,?)";



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

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public static void selectResearchDetails(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_RESEARCH_DETAILS)) {
            List<ResearchDetails> researchDetailsList = new ArrayList<>();
            while (resultSet.next()) {
                ResearchDetails researchDetail = new ResearchDetails();
                researchDetail.setId(resultSet.getInt(COLUMN_RD_ID));
                researchDetail.setName(resultSet.getString(COLUMN_RD_PROGRAM_NAME));
                researchDetail.setOngoing(resultSet.getBoolean(COLUMN_RD_ONGOING));
                researchDetailsList.add(researchDetail);
            }
            if (researchDetailsList.isEmpty()){
                System.out.println("No values at table research details");
            }else {
                for (ResearchDetails myR:researchDetailsList){
                    System.out.println("research id: "+myR.getId()+", program name: "+myR.getName()+", is ongoing: "+myR.isOngoing());
                }
                System.out.println();
            }

        }catch (SQLException e){
            System.out.println("Unable to select from research_details: "+ e.getMessage());

        }
    }

    public static void insertResearchDetails(String program_name,Boolean ongoing){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(INSERT_RESEARCH_DETAILS);) {
            pst.setString(1,program_name);
            pst.setObject(2,ongoing, Types.BOOLEAN);

            pst.executeUpdate();

        }catch (SQLException  e){
            System.out.println("Message: "+ e.getMessage());
        }

    }
}
