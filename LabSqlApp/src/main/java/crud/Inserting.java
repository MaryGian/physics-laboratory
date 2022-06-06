package crud;

import model.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inserting {

    private static final Scanner scanner =new Scanner(System.in);

    private static final String labMembersInsert= "INSERT INTO "+ LabMembers.TABLE_LAB_MEMBERS+"("+LabMembers.COLUMN_MEMBER_NAME+","+LabMembers.COLUMN_MEMBER_SIRNAME+","+LabMembers.COLUMN_MEMBER_EMAIL+","+
    LabMembers.COLUMN_MEMBER_WEBPAGE+","+LabMembers.COLUMN_MEMBER_SHORT_CV+","+LabMembers.COLUMN_MEMBER_ROLE_ID+") "+"VALUES(?,?,?,?,?,?)";

    private static final String announcementsInsert="INSERT INTO "+ Announcements.TABLE_ANNOUNCEMENTS+"("+Announcements.COLUMN_ANNOUNCE_COURSE_ID+","+Announcements.COLUMN_ANNOUNCE_MEMBER_ID+","+
            Announcements.COLUMN_ANNOUNCE_TITLE +","+Announcements.COLUMN_ANNOUNCE_DETAIL+","+Announcements.COLUMN_ANNOUNCE_DATE+") "+"VALUES(?,?,?,?,?)";

    private static final String cowriteInsert="INSERT INTO "+ CoWrite.TABLE_CO_WRITE+"("+CoWrite.COLUMN_COWRITE_COAUTHOR_ID+","+CoWrite.COLUMN_COWRITE_PUB_ID+") "+"VALUES(?,?)";

    private static final String publishInsert="INSERT INTO "+ Publish.TABLE_PUBLISH+"("+Publish.COLUMN_PUBLISH_PUBLICATION_ID+","+Publish.COLUMN_PUBLISH_MEMBER_ID+")"
            +"VALUES(?,?)";

    private static final String researchInsert="INSERT INTO "+Research.TABLE_RESEARCH+"("+Research.COLUMN_RESEARCH_RESEARCH_ID+","+Research.COLUMN_RESEARCH_MEMBER_ID+")"+
            "VALUES(?,?)";

    private static final String tookplaceInsert="INSERT INTO "+TookPlace.TABLE_TOOK_PLACE+"("+TookPlace.COLUMN_TP_RESEARCH_ID+","+TookPlace.COLUMN_TP_INSTITUTE_ID+") VALUES(?,?)";


    private static final String teachInsert="INSERT INTO "+Teach.TABLE_TEACH+"("+Teach.COLUMN_TEACH_MEMBER_ID+","+Teach.COLUMN_TEACH_COURSE_ID+","+Teach.COLUMN_TEACH_HOURS+") VALUES(?,?,?)";




    public static void insertTables() {
        boolean flag = true;
        while (flag) {
            System.out.println("Choose from the list bellow the table that you want to insert new values" +
                    "\n1.Announcements" +
                    "\n2.Co-authors" +
                    "\n3.Co-write" +
                    "\n4.Institutes" +
                    "\n5.Lab Members" +
                    "\n6.Publications" +
                    "\n7.Publish" +
                    "\n8.Research" +
                    "\n9.Research Details" +
                    "\n10.Teach" +
                    "\n11.Took Place" +
                    "\n12.Go back to main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    insertAnnouncements();
                    break;
                case "2":
                    insertCoauthors();
                    break;
                case "3":
                    insertCoWrite();
                    break;
                case "4":
                    insertInstitutes();
                    break;
                case "5":
                    insertLabMembers();
                    break;
                case "6":
                    insertPublications();
                    break;
                case "7":
                    insertPublish();
                    break;
                case "8":
                    insertResearch();
                    break;
                case "9":
                    insertResearchDetails();
                    break;
                case "10":
                    insertTeach();
                    break;
                case "11":
                    insertTookPlace();
                    break;
                case "12":
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose from the list.");
                    break;
            }

            System.out.println("Press enter to continue");
            scanner.nextLine();
            scanner.nextLine();
        }
    }





    private static Integer returnNullByCondition(String something){
        boolean flag= true;
        for (int i=0; i<something.length();i++)
            if (!Character.isDigit(something.charAt(i))){
                flag=false;
            }
        if (something.isEmpty() || !flag){
            return null;
        }else {
            return Integer.parseInt(something);
        }
    }
    private   static void insertLabMembers(){
        String sirname;
        String name;
        String email;
        String short_cv;
        String web_page;
        int role_id;
        System.out.println("Enter the Sir-name");
        sirname=scanner.nextLine();
        System.out.println("Enter the little name");
        name=scanner.nextLine();
        System.out.println("Enter the mail");
        email=scanner.nextLine();
        System.out.println("Enter a small cv");
        short_cv=scanner.nextLine();
        System.out.println("Enter web-page");
        web_page=scanner.nextLine();
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(labMembersInsert)) {
            System.out.println("Enter the role id (1-7)");
            role_id=scanner.nextInt();

            pst.setString(1,name);
            pst.setString(2,sirname);
            pst.setString(3,email);
            pst.setString(4,web_page);
            pst.setString(5,short_cv);
            pst.setInt(6,role_id);

            pst.executeUpdate();
            System.out.println("Success");

        }catch (SQLException  | InputMismatchException e){
            System.out.println("Message: "+ e.getMessage());

        }

    }

    private static void insertAnnouncements(){
        Integer member_id;
        Integer course_id;
        String  title;
        String date;
        String details;
        try( PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(announcementsInsert)) {
            System.out.println("Enter the member id. If this announcement is for no member press enter or any letter.");
            member_id=returnNullByCondition(scanner.nextLine());
            System.out.println("Enter the course id. If this announcement is for no course press enter or any letter.");
            course_id=returnNullByCondition(scanner.nextLine());
            System.out.println("Enter the title");
            title=scanner.nextLine();
            System.out.println("Enter the details");
            details=scanner.nextLine();
            System.out.println("Enter the date. Format: YYYY-MM-DD");
            date=scanner.nextLine();
            pst.setObject(1,course_id, Types.INTEGER);
            pst.setObject(2,member_id, Types.INTEGER);
            pst.setString(3,title);
            pst.setString(4,details);
            pst.setString(5,date);
            pst.executeUpdate();
            System.out.println("Success");

        }catch (SQLException e){
            System.out.println("Invalid insert: "+e.getMessage());
        }

    }




    private   static void insertCoWrite(){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(cowriteInsert)) {
            System.out.println("Enter co-author id.");
            int coauthor_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter publication id.");
            int publication_id=scanner.nextInt();
            scanner.nextLine();
            pst.setObject(1,coauthor_id, Types.INTEGER);
            pst.setObject(2,publication_id, Types.INTEGER);

            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | InputMismatchException e){
            System.out.println("Message: "+ e.getMessage());
        }

    }

    private static void insertPublish(){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(publishInsert)) {
            System.out.println("Enter publication id");
            int publication_Id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter member id");
            int member_id=scanner.nextInt();
            scanner.nextLine();
            pst.setInt(1,publication_Id);
            pst.setInt(2,member_id);

            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException  e){
            System.out.println("Message: "+ e.getMessage());
        }

    }

    private static void insertResearch(){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(researchInsert)) {
            System.out.println("Enter the research id.");
            int research_id=scanner.nextInt();
            scanner.nextLine();
            pst.setInt(1,research_id);
            System.out.println("Enter the member id");
            int member_id=scanner.nextInt();
            scanner.nextLine();
            pst.setInt(2,member_id);

            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException  e){
            System.out.println("Message: "+ e.getMessage());
        }

    }

    private static void insertTookPlace(){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(tookplaceInsert)) {
            System.out.println("Enter the research id.");
            int research_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the institute id");
            int institute_id=scanner.nextInt();
            scanner.nextLine();

            pst.setInt(1,research_id);
            pst.setInt(2,institute_id);
            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException  e){
            System.out.println("Message: "+ e.getMessage());
        }

    }
    private static void insertTeach(){
        try(PreparedStatement pst = ConnectionPool.getConnection().prepareStatement(teachInsert)) {
            System.out.println("Enter member id.");
            int member_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter publication id.");
            int course_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the number of hours per week that this member teaches this course ");
            int hours=scanner.nextInt();
            pst.setInt(1,member_id);
            pst.setInt(2,course_id);
            pst.setInt(3,hours);

            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | InputMismatchException e){
            System.out.println("Message: "+ e.getMessage());
        }

    }

    private static void insertPublications(){
        boolean ongoing;
        try(CallableStatement statement= ConnectionPool.getConnection().prepareCall("call InsertAllForResearch(?,?,?,?)")) {
            System.out.println("Enter the member id that it has participate at this program");
            int member_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the programs' name");
            String programName=scanner.nextLine();
            System.out.println("Enter 1 if the program is still on going, else press anything");
            String isIt=scanner.nextLine();
            if (isIt.equals("1")){
                ongoing=true;
            }else {
                ongoing=false;
            }
            System.out.println("Enter the name of the institute where the program took place");
            String institute=scanner.nextLine();
            statement.setInt(1,member_id);
            statement.setString(2,programName);
            statement.setBoolean(3,ongoing);
            statement.setString(4,institute);
            statement.execute();

            System.out.println("Success");


        }catch (SQLException | InputMismatchException e){
            System.out.println(e.getMessage());
        }

    }

    private static void insertResearchDetails(){
        boolean conference;
        boolean coauthor;
        try(CallableStatement statement= ConnectionPool.getConnection().prepareCall("call InsertPublications(?,?,?,?,?,?,?)")) {
            System.out.println("Enter the member id that it has write publication");
            int member_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the category id for this publication");
            int cat_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the publications title");
            String title=scanner.nextLine();
            System.out.println("Enter the name of the publisher");
            String publisher=scanner.nextLine();
            System.out.println("Enter the date of publish. Format: YYYY-MM-DD");
            String date=scanner.nextLine();
            System.out.println("If it has been published at conference press 1, else if it has publish at journal press anything.");
            String what=scanner.nextLine();
            if (what.equals("1")){
                conference=true;
            }else {
                conference=false;
            }
            System.out.println("If it has co-authors that are not from this lab press 1, else press anything.");
            what=scanner.nextLine();
            if (what.equals("1")){
                coauthor=true;
            }else {
                coauthor=false;
            }
            statement.setInt(1,member_id);
            statement.setInt(2,cat_id);
            statement.setString(3,title);
            statement.setString(4,publisher);
            statement.setString(5,date);
            statement.setBoolean(6,conference);
            statement.setBoolean(7,coauthor);

            statement.execute();

            System.out.println("Success");
            if (coauthor){
                System.out.println("Is this coauthor in our db already? press 1 if it is");
                if (scanner.nextLine().equals("1")){
                    insertCoWrite();
                }else {
                    insertCoauthors();
                }
            }
        }catch (SQLException | InputMismatchException e){
            System.out.println(e.getMessage());
        }

    }



    private static void insertInstitutes(){
        try(CallableStatement pst = ConnectionPool.getConnection().prepareCall("call InsertInstitutes(?,?)")) {
            System.out.println("Enter research id of the program that took place at this institute");
            int pub_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter institutes' name.");
            String name=scanner.nextLine();
            scanner.nextLine();
            pst.setInt(1,pub_id);
            pst.setString(2,name);
            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | InputMismatchException e){
            System.out.println("Message: "+ e.getMessage());
        }

    }



    private static void insertCoauthors(){
        try(CallableStatement pst = ConnectionPool.getConnection().prepareCall("call InsertCoauthors(?,?)")) {
            System.out.println("Enter publication id that co-author has co-write.");
            int pub_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter co-authors' name.");
            String name=scanner.nextLine();
            scanner.nextLine();
            pst.setInt(1,pub_id);
            pst.setString(2,name);
            pst.executeUpdate();
            System.out.println("Success");
        }catch (SQLException | InputMismatchException e){
            System.out.println("Message: "+ e.getMessage());
        }

    }
}
