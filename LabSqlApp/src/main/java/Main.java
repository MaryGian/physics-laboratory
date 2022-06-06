import crud.Deleting;
import crud.Inserting;
import crud.SelectingWithStore;
import crud.Updating;
import model.*;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner= new Scanner(System.in);
    public static ConnectionPool connectionPool = new ConnectionPool();
    public static void main(String[] args) throws SQLException {
        boolean flag=true;
        while (flag){
            System.out.println("Welcome to LabSql" +
                    "Press the number of your choice" +
                    "\n1.Simple selects to see the tables and their content" +
                    "\n2.Insert into tables" +
                    "\n3.Delete from tables" +
                    "\n4.Update from tables" +
                    "\n5.Complicated selects" +
                    "\n6.Quit");
                String choice=scanner.nextLine();
                switch (choice){
                    case "1":
                        Datasource.simpleSelect();
                        break;
                    case "2":
                        Inserting.insertTables();
                        break;
                    case "3":
                        Deleting.deleteTables();
                        break;
                    case "4":
                        Updating.updateTables();
                        break;
                    case "5":
                        SelectingWithStore.proceduresSelect();
                        break;
                    case "6":
                        flag=false;
                        break;
                    default:
                        System.out.println("Choose from the menu");
                }


        }



        connectionPool.close();
    }
}
