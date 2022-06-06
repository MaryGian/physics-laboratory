package crud;
import model.*;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Deleting {
    private static final Scanner scanner= new Scanner(System.in);

    public static void deleteTables() {
        boolean flag = true;
        while (flag) {
            System.out.println("Choose from the list bellow the table that you want to delete from" +
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
                    "\n12.ClearEverything"+
                    "\n13.Go back to main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    deleteFromAnnouncements();
                    break;
                case "2":
                    deleteFromCoAuthors();
                    break;
                case "3":
                    deleteFromCoWrite();
                    break;
                case "4":
                    deleteFromInstitutes();
                    break;
                case "5":
                    deleteFromLabMembers();
                    break;
                case "6":
                    deleteFromPublications();
                    break;
                case "7":
                    deleteFromPublish();
                    break;
                case "8":
                    deleteFromResearch();
                    break;
                case "9":
                    deleteFromResearchDetails();
                    break;
                case "10":
                    deleteFromTeach();
                    break;
                case "11":
                    deleteFromTookPlace();
                    break;
                case "12":
                    Clear();
                    break;
                case "13":
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













    private static void deleteFromLabMembers(){
        System.out.println("Enter the member id.");

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            int id=scanner.nextInt();
            statement.executeUpdate("DELETE FROM "+LabMembers.TABLE_LAB_MEMBERS+" WHERE "+LabMembers.COLUMN_MEMBER_ID+"="+id);

        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Lab members "+e.getMessage());
        }
    }


    private static void deleteFromResearch(){

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the research id");
            int r_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the member id");
            int m_id=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+Research.TABLE_RESEARCH+" WHERE "+Research.COLUMN_RESEARCH_RESEARCH_ID+"="+r_id+" AND "+Research.COLUMN_RESEARCH_MEMBER_ID+"="+m_id);


        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Research "+e.getMessage());
        }

    }
    private static void deleteFromResearchDetails(){
        System.out.println("Enter the research id.");
        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            int id=scanner.nextInt();
            statement.executeUpdate("DELETE FROM "+ResearchDetails.TABLE_RESEARCH_DETAILS+" WHERE "+ResearchDetails.TABLE_RESEARCH_DETAILS+"="+id);

        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Research Details "+e.getMessage());
        }
    }

    private static void deleteFromAnnouncements(){
        System.out.println("Enter the announcement id.");

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            int id=scanner.nextInt();
            statement.executeUpdate("DELETE FROM "+Announcements.TABLE_ANNOUNCEMENTS+" WHERE "+Announcements.COLUMN_ANNOUNCE_ID+"="+id);
            System.out.println("Successfully deleted");
        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Announcements "+e.getMessage());
        }
    }

    private static void deleteFromPublications(){
        System.out.println("Enter the publication id.");

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            int id=scanner.nextInt();
            statement.executeUpdate("DELETE FROM "+Publications.TABLE_PUBLICATIONS+" WHERE "+Publications.COLUMN_PUB_ID+"="+id);

        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Publications "+e.getMessage());
        }
    }

    private static void deleteFromTeach(){
        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the course id");
            int c_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the member id");
            int m_id=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+Teach.TABLE_TEACH+" WHERE "+Teach.COLUMN_TEACH_COURSE_ID+"="+c_id+" AND "+Teach.COLUMN_TEACH_MEMBER_ID+"="+m_id);


        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Research "+e.getMessage());
        }

    }

    private static void deleteFromPublish(){
        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the Publication id");
            int p_id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the member id");
            int m_id=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+Publish.TABLE_PUBLISH+" WHERE "+Publish.COLUMN_PUBLISH_PUBLICATION_ID+"="+p_id+" AND "+Publish.COLUMN_PUBLISH_MEMBER_ID+"="+m_id);


        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Research "+e.getMessage());
        }

    }

    private  static void deleteFromCoAuthors(){
        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the co-authors' id");
            int cid=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+Coauthors.TABLE_CO_AUTHORS+" WHERE "+Coauthors.COLUMN_COAUTHORS_ID+"="+cid);

        }catch (SQLException | InputMismatchException e){
            System.out.println("Could't delete from co-authors: "+e.getMessage());
        }
    }

    private static  void deleteFromCoWrite(){

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the co-authors' id");
            int authorid=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the pub id");
            int pubid=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+CoWrite.TABLE_CO_WRITE+" WHERE "+CoWrite.COLUMN_COWRITE_COAUTHOR_ID+"="+authorid+" AND "+CoWrite.COLUMN_COWRITE_PUB_ID+"="+pubid);



        }catch (SQLException | InputMismatchException e){
            System.out.println("Could't delete from co-write: "+e.getMessage());
        }
    }
    private static  void deleteFromTookPlace(){

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            System.out.println("Enter the institutes' id");
            int instid=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the pub id");
            int pubid=scanner.nextInt();
            scanner.nextLine();
            statement.executeUpdate("DELETE FROM "+TookPlace.TABLE_TOOK_PLACE+" WHERE "+TookPlace.COLUMN_TP_INSTITUTE_ID+"="+instid+" AND "+TookPlace.COLUMN_TP_RESEARCH_ID+"="+pubid);



        }catch (SQLException | InputMismatchException e){
            System.out.println("Could't delete from co-write: "+e.getMessage());
        }
    }

    private static void deleteFromInstitutes(){
        System.out.println("Enter the institute id.");

        try(Statement statement=ConnectionPool.getConnection().createStatement()) {
            int id=scanner.nextInt();
            statement.executeUpdate("DELETE FROM "+Institutes.TABLE_INSTITUTES+" WHERE "+Institutes.COLUMN_INSTITUTE_ID+"="+id);

        }catch (SQLException | InputMismatchException e){
            System.out.println("Couldn't delete from Publications "+e.getMessage());
        }
    }

    private static void Clear(){
        try (CallableStatement statement= ConnectionPool.getConnection().prepareCall("CALL ClearEverything()")){

            statement.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
