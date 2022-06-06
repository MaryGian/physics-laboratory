package crud;
import model.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Updating {
    private static final Scanner scan = new Scanner(System.in);
    private static final String publishUpdate="UPDATE publish SET pub_id=? and member_id=? where pub_id=? and member_id=?";
    private static final String researchUpdate="UPDATE research SET research_id=? and member_id=? where research_id=? and member_id=?";
    private static final String tookPlaceUpdate="UPDATE took_place SET research_id=? and institute_id=? where research_id=? and institute_id=?";
    private static final String cowriteUpdate="UPDATE co_write SET pub_id=? and cauthor_id=? where pub_id=? and cauthor_id=?";
    private static final String coauthorsUpdate="UPDATE co_authors SET cauthor_name=? WHERE cauthor_id=?";
    private static final String institutesUpdate="UPDATE institutes SET institute_name=? where institute_id=?";




    public static void updateTables() {
        boolean flag = true;
        while (flag) {
            System.out.println("Choose from the list bellow the table that you want to update from" +
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
            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    updateAnnouncements();
                    break;
                case "2":
                    updateCoauthors();
                    break;
                case "3":
                    updateCoWrite();
                    break;
                case "4":
                    updateInstitutes();
                    break;
                case "5":
                    updateLabmembers();
                    break;
                case "6":
                    updatePublications();
                    break;
                case "7":
                    updatePublish();
                    break;
                case "8":
                    updateResearch();
                    break;
                case "9":
                    updateResearchDetails();
                    break;
                case "10":
                    updateTeach();
                    break;
                case "11":
                    updateTookPlace();
                    break;
                case "12":
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose from the list.");
                    break;
            }

            System.out.println("Press enter to continue");
            scan.nextLine();
            scan.nextLine();
        }
    }


    private static void updateLabmembers(){

            while (true) {

                System.out.println("Press the number of the field you want to update for the specific member" +
                        "\n1.Role id" +
                        "\n2.Members' name" +
                        "\n3.Members' sir-name" +
                        "\n4.Email" +
                        "\n5.Cv" +
                        "\n6.web page" +
                        "\n7.Stop updating and go back to menu for updating");
                try {
                    String updateLabmembers;
                    PreparedStatement statement;
                    int column = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the new value of this field");
                    if (column == 1) {
                        updateLabmembers="UPDATE lab_members"+" SET role_id= ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setInt(1, scan.nextInt());
                        scan.nextLine();
                    } else if (column == 2) {
                        updateLabmembers="UPDATE lab_members"+" SET member_name= ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setString(1, scan.nextLine());
                    } else if (column == 3) {
                        updateLabmembers="UPDATE lab_members"+" SET sir_name= ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setString(1, scan.nextLine());
                    } else if (column == 4) {
                        updateLabmembers="UPDATE lab_members"+" SET email= ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setString(1, scan.nextLine());
                    } else if (column == 5) {
                        updateLabmembers="UPDATE lab_members"+" SET short_cv = ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setString(1, scan.nextLine());
                    } else if (column == 6) {
                        updateLabmembers="UPDATE lab_members"+" SET web_page= ?"+" WHERE member_id = ?";
                        statement=ConnectionPool.getConnection().prepareStatement(updateLabmembers);
                        statement.setString(1, scan.nextLine());
                    } else if (column == 7) {
                        break;
                    } else {
                        System.out.println("You did not choose from the list, try again from start");
                        continue;
                    }

                    System.out.println("Press the members id you want to update");
                    int member_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(2, member_id);
                    statement.executeUpdate();
                    System.out.println("Success");
                    statement.close();

                } catch (InputMismatchException | SQLException e) {
                    System.out.println(e.getMessage());
                    scan.nextLine();
                }
            }
    }



    public static void updatePublish(){
        while (true){
            try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(publishUpdate)) {
                System.out.println("If you want to continue updating table Publish press 1 else press anything");
                String number=scan.nextLine();
                if (number.equals("1")) {
                    System.out.println("Press the publication id where you want to make the update on");
                    int pub_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(3, pub_id);
                    System.out.println("Press the member id where you want to make the update on");
                    int member_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(4, member_id);
                    System.out.println("Press the new publication id");
                    int newpub_id = scan.nextInt();
                    statement.setInt(1, newpub_id);
                    scan.nextLine();
                    System.out.println("Press the new member id");
                    int newmember_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(2, newmember_id);
                    statement.executeUpdate();

                }else {
                    break;
                }
            }catch (InputMismatchException | SQLException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }

        }
    }





    private static void updateTeach(){

        while (true) {

            System.out.println("Press the number of the field you want to update for the specific member" +
                    "\n1.Course id" +
                    "\n2.Member id" +
                    "\n3.Teaching hours per week" +
                    "\n4.Stop updating and go back to menu for updating");
            try {
                String updateTeach;
                PreparedStatement statement;
                int column = scan.nextInt();
                scan.nextLine();

                System.out.println("Enter the new value of this field");
                if (column == 1) {
                    updateTeach="UPDATE teach"+" SET course_id= ?"+" WHERE course_id = ? and member_id= ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateTeach);
                    statement.setInt(1, scan.nextInt());
                    scan.nextLine();
                } else if (column == 2) {
                    updateTeach="UPDATE teach"+" SET member_id= ?"+" WHERE course_id = ? and member_id= ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateTeach);
                    statement.setString(1, scan.nextLine());
                } else if (column == 3) {
                    updateTeach="UPDATE teach"+" SET hours_for_each= ?"+" WHERE course_id = ? and member_id= ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateTeach);
                    statement.setString(1, scan.nextLine());
                } else if (column == 7) {
                    break;
                } else {
                    System.out.println("You did not choose from the list, try again from start");
                    continue;
                }

                System.out.println("Press the members id where you want to make the update");
                int member_id = scan.nextInt();
                scan.nextLine();
                statement.setInt(3, member_id);
                System.out.println("Press the course id where you want to make the update");
                int course_id = scan.nextInt();
                scan.nextLine();
                statement.setInt(2, course_id);
                statement.executeUpdate();

                statement.close();

            } catch (InputMismatchException | SQLException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }
    }

    private static void updateResearch(){
        while (true){
            try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(researchUpdate)) {
                System.out.println("If you want to continue updating table Research press 1 else press anything");
                String number=scan.nextLine();
                if (number.equals("1")) {
                    System.out.println("Press the research id where you want to make the update on");
                    int research_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(3, research_id);
                    System.out.println("Press the member id where you want to make the update on");
                    int member_id = scan.nextInt();
                    statement.setInt(4, member_id);
                    System.out.println("Press the new research id");
                    int newr_id = scan.nextInt();
                    statement.setInt(1, newr_id);
                    scan.nextLine();
                    System.out.println("Press the new member id");
                    int newmember_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(2, newmember_id);
                    statement.executeUpdate();

                }else {
                    break;
                }
            }catch (InputMismatchException | SQLException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }

        }
    }

    private static void updateTookPlace(){
        while (true){
            try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(tookPlaceUpdate)) {
                System.out.println("If you want to continue updating table Took_place press 1 else press anything");
                String number=scan.nextLine();
                if (number.equals("1")) {
                    System.out.println("Press the research id where you want to make the update on");
                    int pub_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(3, pub_id);
                    System.out.println("Press the institute id where you want to make the update on");
                    int inst_id = scan.nextInt();
                    statement.setInt(4, inst_id);
                    System.out.println("Press the new research id");
                    int newpub_id = scan.nextInt();
                    statement.setInt(1, newpub_id);
                    scan.nextLine();
                    System.out.println("Press the new institute id");
                    int newinst_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(2, newinst_id);
                    statement.executeUpdate();
                }else {
                    break;
                }
            }catch (InputMismatchException | SQLException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }

        }
    }



    private static void updateCoWrite(){
        while (true){
            try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(cowriteUpdate)) {
                System.out.println("If you want to continue updating table Co-write press 1 else press anything");
                String number=scan.nextLine();
                if (number.equals("1")) {
                    System.out.println("Press the publication id where you want to make the update on");
                    int pub_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(3, pub_id);
                    System.out.println("Press the co-author id where you want to make the update on");
                    int cauthor_id = scan.nextInt();
                    statement.setInt(4, cauthor_id);
                    System.out.println("Press the new publication id");
                    int newpub_id = scan.nextInt();
                    statement.setInt(1, newpub_id);
                    scan.nextLine();
                    System.out.println("Press the new co-author id");
                    int newcauthor_id = scan.nextInt();
                    scan.nextLine();
                    statement.setInt(2, newcauthor_id);
                    statement.executeUpdate();
                    System.out.println("Success");
                }else {
                    break;
                }
            }catch (InputMismatchException | SQLException e){
                System.out.println(e.getMessage());
                scan.nextLine();
            }

        }
    }
    private static void updateCoauthors(){
        while (true){
            System.out.println("If you want to keep updating table co-authors press 1, else press anything else.");
            String num =scan.nextLine();
            if (num.equals("1")){
                try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(coauthorsUpdate)) {
                    System.out.println("Enter the institute id you want to make the update on.");
                    int inst_id=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the new name of the Coauthor you want to update");
                    String name=scan.nextLine();
                    statement.setInt(2,inst_id);
                    statement.setString(1,name);

                }catch (SQLException | InputMismatchException e){
                    System.out.println(e.getMessage());
                    scan.nextLine();
                }

            }else {
                break;
            }
        }
    }



    private static void updateInstitutes(){
        while (true){
            System.out.println("if you want to continue updating table Institutes press 1, else press anything");
            String num=scan.nextLine();
            if (num.equals("1")){

                try(PreparedStatement statement=ConnectionPool.getConnection().prepareStatement(institutesUpdate)) {
                    System.out.println("Enter the institute id you want to make update on.");
                    int id=scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter the new name for the institute you chose");
                    String name=scan.nextLine();
                    statement.setString(1,name);
                    statement.setInt(2,id);
                    statement.executeUpdate();
                }catch (SQLException | InputMismatchException e){
                    System.out.println(e.getMessage());
                    scan.nextLine();
                }
            }else {
                break;
            }
        }
    }



    private static void updatePublications(){
        String what;
        boolean itis;
        while (true) {

            System.out.println("Press the number of the field you want to update for the specific member" +
                    "\n1.Category id" +
                    "\n2.Publication title" +
                    "\n3.Date of publish" +
                    "\n4.Publisher" +
                    "\n5.Publish at conference" +
                    "\n6.Foreign Co-authors" +
                    "\n7.Stop updating and go back to menu for updating");
            try {
                String updatePub;
                PreparedStatement statement;
                int column = scan.nextInt();
                scan.nextLine();

                if (column == 1) {
                    System.out.println("Enter the new value for the category id");

                    updatePub="UPDATE publications"+" SET category_id= ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setInt(1, scan.nextInt());
                    scan.nextLine();
                } else if (column == 2) {
                    System.out.println("Enter the new value for the title");
                    updatePub="UPDATE publications"+" SET pub_title= ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setString(1, scan.nextLine());
                } else if (column == 3) {
                    System.out.println("Enter the new date. Format: YYYY-MM-DD");
                    updatePub="UPDATE publications"+" SET date_of_pub= ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setString(1, scan.nextLine());
                } else if (column == 4) {
                    System.out.println("Enter the new publisher");
                    updatePub="UPDATE publications"+" SET publisher= ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setString(1, scan.nextLine());
                } else if (column == 5) {
                    System.out.println("Enter the new value 1 if publication  has been published at conference, else press anything");
                    what=scan.nextLine();
                    if (what.equals("1")){
                        itis=true;
                    }else {
                        itis=false;
                    }
                    updatePub="UPDATE publications"+" SET publish_at_conference = ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setBoolean(1, itis);
                } else if (column == 6) {
                    System.out.println("Enter the new value 1 if publication  has co-authors which are not in the lab, else press anything");
                    what=scan.nextLine();
                    if (what.equals("1")){
                        itis=true;
                    }else {
                        itis=false;
                    }
                    updatePub="UPDATE publications"+" SET co_author_foreign= ?"+" WHERE pub_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updatePub);
                    statement.setBoolean(1, itis);
                } else if (column == 7) {
                    break;
                } else {
                    System.out.println("You did not choose from the list, try again from start");
                    continue;
                }

                System.out.println("Press the pub id you want to update on");
                int member_id = scan.nextInt();
                scan.nextLine();
                statement.setInt(2, member_id);
                statement.executeUpdate();
                System.out.println("Success");
                statement.close();

            } catch (InputMismatchException | SQLException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }
    }


    private static void updateAnnouncements(){
        while (true) {

            System.out.println("Press the number of the field you want to update for the specific member" +
                    "\n1.Course id" +
                    "\n2.Member id" +
                    "\n3.Title" +
                    "\n4.Detail" +
                    "\n5.Date of the announcement" +
                    "\n6.Stop updating and go back to menu for updating");
            try {
                String updateAnnounc;
                PreparedStatement statement;
                int column = scan.nextInt();
                scan.nextLine();

                if (column == 1) {
                    System.out.println("Enter the new value for the course id");

                    updateAnnounc="UPDATE announcements"+" SET course_id= ?"+" WHERE an_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateAnnounc);
                    statement.setInt(1, scan.nextInt());
                    scan.nextLine();
                } else if (column == 2) {
                    System.out.println("Enter the new value for the member_id");
                    updateAnnounc="UPDATE announcements"+" SET member_id= ?"+" WHERE an_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateAnnounc);
                    statement.setInt(1, scan.nextInt());
                } else if (column == 5) {
                    System.out.println("Enter the new date. Format: YYYY-MM-DD");
                    updateAnnounc="UPDATE announcements"+" SET date_of_announcement= ?"+" WHERE an_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateAnnounc);
                    statement.setString(1, scan.nextLine());
                } else if (column == 3) {
                    System.out.println("Enter the new title");
                    updateAnnounc="UPDATE announcements"+" SET title= ?"+" WHERE an_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateAnnounc);
                    statement.setString(1, scan.nextLine());
                } else if (column == 4) {
                    System.out.println("Enter the new details");
                    updateAnnounc="UPDATE announcements"+" SET detail= ?"+" WHERE an_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateAnnounc);
                    statement.setString(1,scan.nextLine());
                } else if (column == 6) {
                    break;
                } else {
                    System.out.println("You did not choose from the list, try again from start");
                    continue;
                }

                System.out.println("Press the announcement id you want to update on");
                int announc_id = scan.nextInt();
                scan.nextLine();
                statement.setInt(2, announc_id);
                statement.executeUpdate();
                System.out.println("Success");
                statement.close();

            } catch (InputMismatchException | SQLException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }
    }

    private static void updateResearchDetails(){
        String what;
        boolean itis;
        while (true) {

            System.out.println("Press the number of the field you want to update" +
                    "\n1.Program name" +
                    "\n2.Ongoing" +
                    "\n3.Stop updating and go back to menu for updating");
            try {
                String updateRD;
                PreparedStatement statement;
                int column = scan.nextInt();
                scan.nextLine();

                if (column == 1) {
                    System.out.println("Enter the new value for the program name");

                    updateRD="UPDATE research_details"+" SET program_name= ?"+" WHERE research_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateRD);
                    statement.setString(1, scan.nextLine());

                } else if (column == 2) {
                    System.out.println("Enter the new value 1 if you want to update program to still ongoing");
                    what=scan.nextLine();
                    if (what.equals("1")){
                        itis=true;
                    }else {
                        itis=false;
                    }
                    updateRD="UPDATE research_details"+" SET ongoing = ?"+" WHERE research_id = ?";
                    statement=ConnectionPool.getConnection().prepareStatement(updateRD);
                    statement.setBoolean(1, itis);
                } else if (column == 3) {
                    break;
                } else {
                    System.out.println("You did not choose from the list, try again from start");
                    continue;
                }

                System.out.println("Press the research id you want to update on");
                int r_id = scan.nextInt();
                scan.nextLine();
                statement.setInt(2, r_id);
                statement.executeUpdate();
                System.out.println("Success");
                statement.close();

            } catch (InputMismatchException | SQLException e) {
                System.out.println(e.getMessage());
                scan.nextLine();
            }
        }
    }

}
