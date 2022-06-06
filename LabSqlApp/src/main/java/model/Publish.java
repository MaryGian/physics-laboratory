package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Publish {
    private int member_id;
    private int pub_id;

    public static final String TABLE_PUBLISH = "publish";
    public static final String COLUMN_PUBLISH_PUBLICATION_ID = "pub_id";
    public static final String COLUMN_PUBLISH_MEMBER_ID = "member_id";




    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getPub_id() {
        return pub_id;
    }

    public void setPub_id(int pub_id) {
        this.pub_id = pub_id;
    }

    public static void selectPublish(){
        try(Statement statement = ConnectionPool.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+ TABLE_PUBLISH)) {
            List<Publish> publishList = new ArrayList<>();
            while (resultSet.next()){
                Publish publish = new Publish();
                publish.setMember_id(resultSet.getInt(COLUMN_PUBLISH_MEMBER_ID));
                publish.setPub_id(resultSet.getInt(COLUMN_PUBLISH_PUBLICATION_ID));
                publishList.add(publish);
            }
            if (publishList.isEmpty()){
                System.out.println("No values at table Publish");
            }else {
                for (Publish myPub:publishList){
                    System.out.println("member id: "+myPub.getMember_id()+", publications' id: "+myPub.getPub_id());
                }
            }


        }catch (SQLException e){
            System.out.println("Unable to select from publish: "+ e.getMessage());

        }
    }


}
