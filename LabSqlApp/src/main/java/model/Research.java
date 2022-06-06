package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Research {
    private int research_id;
    private int member_id;

    public static final String TABLE_RESEARCH = "research";
    public static final String COLUMN_RESEARCH_MEMBER_ID = "member_id";
    public static final String COLUMN_RESEARCH_RESEARCH_ID = "research_id";



    public int getResearch_id() {
        return research_id;
    }

    public void setResearch_id(int research_id) {
        this.research_id = research_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public static void selectResearch(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_RESEARCH)) {
            List<Research> researchList = new ArrayList<>();
            while (resultSet.next()){
                Research research = new Research();
                research.setResearch_id(resultSet.getInt(COLUMN_RESEARCH_RESEARCH_ID));
                research.setMember_id(resultSet.getInt(COLUMN_RESEARCH_MEMBER_ID));
                researchList.add(research);
            }
            if (researchList.isEmpty()){
                System.out.println("No values at table Research");
            }else {
                for (Research myReaserch:researchList){
                    System.out.println("research id: "+myReaserch.getResearch_id()+", member id: "+myReaserch.getMember_id());
                }
            }

        }catch (SQLException e){
            System.out.println("Unable to select from research: "+ e.getMessage());

        }

    }


}
