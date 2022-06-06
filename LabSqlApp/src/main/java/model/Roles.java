package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Roles {
    private int id;
    private String name;

    public static final String TABLE_ROLES = "roles";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_ROLE_NAME = "role_name";

    private static final String INSERT_ROLES="INSERT INTO"+TABLE_ROLES+"("+COLUMN_ROLE_NAME+") VALUES(?)";


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void selectRoles() {
        try (Statement statement = ConnectionPool.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_ROLES)) {
            List<Roles> rolesList = new ArrayList<>();
            while (resultSet.next()) {
                Roles role = new Roles();
                role.setId(resultSet.getInt(COLUMN_ROLE_ID));
                role.setName(resultSet.getString(COLUMN_ROLE_NAME));
                rolesList.add(role);
            }
            if (rolesList.isEmpty()){
                System.out.println("No values at table Roles");
            }else {
                for (Roles myR:rolesList) {
                    System.out.println("role id: " +myR.getId()+ ", role: "+ myR.getName());
                }
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Unable to select from roles: " + e.getMessage());

        }

    }


}
