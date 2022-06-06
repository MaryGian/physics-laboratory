package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Datasource {
    private static final Scanner scanner = new Scanner(System.in);

    public static void simpleSelect() {
        boolean flag = true;
        while (flag) {
            System.out.println("Choose from the list bellow" +
                    "\n1.Announcements" +
                    "\n2.Co-authors" +
                    "\n3.Courses" +
                    "\n4.Co-write" +
                    "\n5.Institutes" +
                    "\n6.Lab Members" +
                    "\n7.Publications categories" +
                    "\n8.Publications" +
                    "\n9.Publish" +
                    "\n10.Research" +
                    "\n11.Research Details" +
                    "\n12.Roles" +
                    "\n13.Teach" +
                    "\n14.Took Place" +
                    "\n15.Go back to main menu");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Announcements.selectAnnouncements();
                    break;
                case "2":
                    Coauthors.selectCoauthors();
                    break;
                case "3":
                    Courses.selectCourses();
                    break;
                case "4":
                    CoWrite.selectCowrite();
                    break;
                case "5":
                    Institutes.selectInstitutes();
                    break;
                case "6":
                    LabMembers.selectLabMembers();
                    break;
                case "7":
                    PubCategory.selectPubCategory();
                    break;
                case "8":
                    Publications.selectPublications();
                    break;
                case "9":
                    Publish.selectPublish();
                    break;
                case "10":
                    Research.selectResearch();
                    break;
                case "11":
                    ResearchDetails.selectResearchDetails();
                    break;
                case "12":
                    Roles.selectRoles();
                    break;
                case "13":
                    Teach.selectTeach();
                    break;
                case "14":
                    TookPlace.searchTookPlace();
                    break;
                case "15":
                    flag = false;
                    break;
                default:
                    System.out.println("Please choose from the list.");
                    break;
            }
            System.out.println("Press enter to continue");
            scanner.nextLine();
        }
    }


}
