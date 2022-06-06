package crud;

import model.ConnectionPool;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectingWithStore {
    private static final Scanner scanner = new Scanner(System.in);

    public static void proceduresSelect(){
        boolean flag=true;
        while (flag){
            System.out.println("Choose from the list bellow:" +
                    "\n1. Number of Publications for all the members" +
                    "\n2.The announcements for a member or a course of your choice" +
                    "\n3.Publications that have been made a given year of your choice and published or at conference or at journal"+
                    "\n4.The teachers and the hours they teach per week for a course of your choice" +
                    "\n5.Publications of the member you want" +
                    "\n6.See all the couples of members that have publish together" +
                    "\n7.See all the couples of member tha have participate at the same program" +
                    "\n8.Go back to main menu");
            String choice= scanner.nextLine();
            switch (choice){
                case "1":
                    numberOfPublications();
                    break;
                case "2":
                    announcementsForMemberOrCourse();
                    break;
                case"3":
                    publicationsByYearAndPublishedAt();
                    break;
                case "4":
                    teachersForCourses();
                    break;
                case "5":
                    publicationsForAMember();
                    break;
                case "6":
                    couplesPublications();
                    break;
                case "7":
                    couplesOfResearch();
                    break;
                case "8":
                    flag=false;
                default:
                    System.out.println("please choose from the list");
                    break;

            }

            System.out.println("press enter to continue");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    private static void publicationsByYearAndPublishedAt(){
        boolean where;
        while (true) {
            try (CallableStatement callableStatement = ConnectionPool.getConnection().prepareCall("call PublicationsBooleanConferenceAndYear(?,?)")) {
                System.out.println("Enter the year for the publications you want to find. Format: YYYY" +
                        "\nIf you want to go back enter 0 ");
                int year = scanner.nextInt();
                scanner.nextLine();
                if (year==0){
                    break;
                }
                System.out.println("Do you want to be publications publish at conference? If yes press 1 else press anything");
                String choice=scanner.nextLine();
                if (choice.equals("1")){
                    where=true;
                } else {
                    where=false;
                }
                callableStatement.setBoolean(1,where);
                callableStatement.setInt(2,year);
                ResultSet resultSet=callableStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println("member id:"+resultSet.getInt(1)+"| Sir Name:"+resultSet.getString(2)+
                            "| publication id:"+resultSet.getInt(3)+ "| category:"+resultSet.getString(4)+
                            "| title:"+resultSet.getString(5)+"| date:"+resultSet.getDate(6)+"| publisher:" +
                            resultSet.getString(7));
                }
                System.out.println("press enter to continue");
                scanner.nextLine();
                resultSet.close();
            } catch (SQLException | InputMismatchException | NullPointerException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }


    private static void announcementsForMemberOrCourse(){
        String where;
        while (true) {
            try (CallableStatement callableStatement = ConnectionPool.getConnection().prepareCall("call AnnounceForMemberOrCourse(?,?)")) {
                System.out.println("1.Enter 1 if you want to search the announcements for a specific course" +
                        "\n2.Enter 0 if you want to go back" +
                        "\n3.Enter anything else if you want to search for a specific member");
                int some = scanner.nextInt();
                scanner.nextLine();
                if (some==0){
                    break;
                }else if (some==1){
                    where="course";
                }else {
                    where="member";
                }
                System.out.println("Enter the id for your search");
                int id=scanner.nextInt();
                scanner.nextLine();
                callableStatement.setString(1,where);
                callableStatement.setInt(2,id);
                ResultSet resultSet=callableStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println("announcement id:"+resultSet.getInt(1)+"| Title:"+resultSet.getString(2)+
                             "| detail:"+resultSet.getString(3)+
                            "| date:"+resultSet.getDate(4));
                }
                resultSet.close();
                System.out.println("press enter to continue");
                scanner.nextLine();
            } catch (SQLException | InputMismatchException | NullPointerException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }

    private static void publicationsForAMember(){
        while (true){
            System.out.println("Enter the member id you want to search for or press 0 to go back");
            try(CallableStatement callableStatement=ConnectionPool.getConnection().prepareCall("call MembersPublications(?)")) {
                int id=scanner.nextInt();
                if (id==1){
                    break;
                }
                callableStatement.setInt(1,id);
                ResultSet resultSet=callableStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println("Sir Name:"+resultSet.getString(1)+"| publication id:"+resultSet.getInt(2)+
                            "| title:"+resultSet.getString(2)+
                            "| date:"+resultSet.getDate(5)+
                            "| publisher:"+resultSet.getString(6));
                }
                resultSet.close();
                System.out.println("Press enter to continue");
                scanner.nextLine();
            }catch (SQLException | InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }

        }
    }

    private static void teachersForCourses(){
        while (true){
            System.out.println("Enter the id of the course, enter 0 to go back");
            try(CallableStatement callableStatement=ConnectionPool.getConnection().prepareCall("CALL TeachingCourses(?)")) {
                int id=scanner.nextInt();
                scanner.nextLine();
                if (id==0){
                    break;
                }
                callableStatement.setInt(1,id);
                ResultSet resultSet=callableStatement.executeQuery();
                while (resultSet.next()){
                    System.out.println("course name:"+resultSet.getString(1)+
                            "| member id:"+resultSet.getInt(2)+
                            "| Sir-Name:"+resultSet.getString(3)+"| teaching hours:"+resultSet.getInt(4));
                }
                resultSet.close();
                System.out.println("press enter to continue");
                scanner.nextLine();
            }catch (InputMismatchException | SQLException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }


    private static void couplesPublications(){
        try(CallableStatement callableStatement=ConnectionPool.getConnection().prepareCall("call PublicationsOfCouples()");
        ResultSet resultSet=callableStatement.executeQuery()) {
            while (resultSet.next()){
                System.out.println("publication id:"+resultSet.getInt(1)+
                        " | title:"+resultSet.getString(2)+
                        " | member id:"+resultSet.getInt(3)+
                        " | Sir-Name:"+resultSet.getString(4)+
                        " | member id:"+resultSet.getInt(5)+
                        " | Sir-Name:"+resultSet.getString(6));

            }
            System.out.println("press enter to continue");
            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }



    public static void couplesOfResearch(){
        try(CallableStatement callableStatement=ConnectionPool.getConnection().prepareCall("call ResearchOfCouples()");
            ResultSet resultSet=callableStatement.executeQuery()) {
            while (resultSet.next()){
                System.out.println("research id:"+resultSet.getInt(1)+
                        " | program name:"+resultSet.getString(2)+
                        " | member id:"+resultSet.getInt(3)+
                        " | Sir-Name:"+resultSet.getString(4)+
                        " | member id:"+resultSet.getInt(5)+
                        " | Sir-Name:"+resultSet.getString(6));

            }
            System.out.println("press enter to continue");
            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    private static void numberOfPublications(){
        try(CallableStatement callableStatement=ConnectionPool.getConnection().prepareCall("call NumberOfPublications()");
            ResultSet resultSet=callableStatement.executeQuery()) {
            while (resultSet.next()){
                System.out.println("member id:"+resultSet.getInt(1)+
                        " | member Sir-Name:"+resultSet.getString(2)+
                        " | number of publications:"+resultSet.getInt(3));

            }
            System.out.println("press enter to continue");
            scanner.nextLine();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
