package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabMembers {


    private int id;
    private String name;
    private String sirname;
    private String email;
    private int role_id;
    private String short_cv;
    private String web_page;

    public static final String TABLE_LAB_MEMBERS = "lab_members";
    public static final String COLUMN_MEMBER_ID = "member_id";
    public static final String COLUMN_MEMBER_NAME = "member_name";
    public static final String COLUMN_MEMBER_SIRNAME = "sir_name";
    public static final String COLUMN_MEMBER_EMAIL = "email";
    public static final String COLUMN_MEMBER_WEBPAGE = "web_page";
    public static final String COLUMN_MEMBER_SHORT_CV = "short_cv";
    public static final String COLUMN_MEMBER_ROLE_ID = "role_id";



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setShort_cv(String short_cv) {
        this.short_cv = short_cv;
    }

    public String getWeb_page() {
        return web_page;
    }

    public void setWeb_page(String web_page) {
        this.web_page = web_page;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSirname() {
        return sirname;
    }

    public String getEmail() {
        return email;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getShort_cv() {
        return short_cv;
    }


    public static void selectLabMembers(){
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_LAB_MEMBERS)) {
            List<LabMembers> labMembersList = new ArrayList<>();
            while (resultSet.next()){
                LabMembers labMember = new LabMembers();
                labMember.setId(resultSet.getInt(COLUMN_MEMBER_ID));
                labMember.setName(resultSet.getString(COLUMN_MEMBER_NAME));
                labMember.setSirname(resultSet.getString(COLUMN_MEMBER_SIRNAME));
                labMember.setEmail(resultSet.getString(COLUMN_MEMBER_EMAIL));
                labMember.setRole_id(resultSet.getInt(COLUMN_MEMBER_ROLE_ID));
                labMember.setShort_cv(resultSet.getString(COLUMN_MEMBER_SHORT_CV));
                labMember.setWeb_page(resultSet.getString(COLUMN_MEMBER_WEBPAGE));
                labMembersList.add(labMember);
            }

            if (labMembersList.isEmpty()){
                System.out.println("you did stupid");
            }else{
                for (LabMembers myMember: labMembersList){
                    System.out.println("member id: "+myMember.getId()+", SirName: "+myMember.getSirname()+", Name: "+myMember.getName()+
                            ", role id: "+myMember.getRole_id()+", email: "+myMember.getEmail()+", web-page: "+ myMember.getWeb_page()+"\n short-cv: "+myMember.getShort_cv());
                }
            }

            System.out.println();
        }catch (SQLException e){
            System.out.println("Unable to select: "+ e.getMessage());

        }

    }



}
