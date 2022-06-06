package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PubCategory {
    private int id;
    private String name;

    public static final String TABLE_PUBLICATIONS_CATEGORY = "pub_category";
    public static final String COLUMN_PUBCATEGORY_ID = "category_id";
    public static final String COLUMN_PUBCATEGORY_NAME = "category_name";



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

    public static void selectPubCategory(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_PUBLICATIONS_CATEGORY)) {

            List<PubCategory> pubCategoryList =  new ArrayList<>();
            while (resultSet.next()){
                PubCategory pubCategory = new PubCategory();
                pubCategory.setId(resultSet.getInt(COLUMN_PUBCATEGORY_ID));
                pubCategory.setName(resultSet.getString(COLUMN_PUBCATEGORY_NAME));
                pubCategoryList.add(pubCategory);
            }

            if (pubCategoryList.isEmpty()){
                System.out.println("No values at table pub_category");
            }else {
                for (PubCategory myPubCategory: pubCategoryList){
                    System.out.println("category id: "+ myPubCategory.getId()+", category name: "+myPubCategory.getName());
                }
            }

            System.out.println();
        }catch (SQLException e){
            System.out.println("Unable to select from Pub_Category: "+ e.getMessage());

        }
    }



}
