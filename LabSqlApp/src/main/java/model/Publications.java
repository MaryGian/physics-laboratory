package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Publications {
    private int id;
    private String title;
    private String date;
    private String publisher;
    private int category_id;
    private boolean foreign_coauthors;
    private boolean publish_at_conference;

    public static final String TABLE_PUBLICATIONS = "publications";
    public static final String COLUMN_PUB_ID = "pub_id";
    public static final String COLUMN_PUB_CATEGORY_ID = "category_id";
    public static final String COLUMN_PUB_TITLE = "pub_title";
    public static final String COLUMN_PUB_DATE = "date_of_pub";
    public static final String COLUMN_PUB_PUBLISHER = "publisher";
    public static final String COLUMN_PUB_AT_CONFERENCE = "publish_at_conference";
    public static final String COLUMN_PUB_CO_AUTHORS = "co_author_foreign";




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public boolean isForeign_coauthors() {
        return foreign_coauthors;
    }

    public void setForeign_coauthors(boolean foreign_coauthors) {
        this.foreign_coauthors = foreign_coauthors;
    }

    public boolean isPublish_at_conference() {
        return publish_at_conference;
    }

    public void setPublish_at_conference(boolean publish_at_conference) {
        this.publish_at_conference = publish_at_conference;
    }

    public static void selectPublications(){
        String isItPublished;
        try(Statement statement= ConnectionPool.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery("SELECT * FROM "+TABLE_PUBLICATIONS)) {
            List<Publications> publicationsList = new ArrayList<>();
            while (resultSet.next()){
                Publications publication = new Publications();
                publication.setCategory_id(resultSet.getInt(COLUMN_PUB_CATEGORY_ID));
                publication.setPublisher(resultSet.getString(COLUMN_PUB_PUBLISHER));
                publication.setId(resultSet.getInt(COLUMN_PUB_ID));
                publication.setDate(resultSet.getString(COLUMN_PUB_DATE));
                publication.setTitle(resultSet.getString(COLUMN_PUB_TITLE));
                publication.setForeign_coauthors(resultSet.getBoolean(COLUMN_PUB_CO_AUTHORS));
                publication.setPublish_at_conference(resultSet.getBoolean(COLUMN_PUB_AT_CONFERENCE));
                publicationsList.add(publication);
            }
            if (publicationsList.isEmpty()){
                System.out.println("No values at table publications");
            }else {
                for (Publications myPub:publicationsList){
                    if (myPub.isPublish_at_conference()){
                        isItPublished="conference";
                    }else {
                        isItPublished="journal";
                    }
                    System.out.println("publication id: "+myPub.getId()+", category id: "+myPub.category_id+
                            ", title: "+myPub.getTitle()+", date of publish: "+myPub.getDate()+ ", publisher: "+
                            myPub.getPublisher()+", published at: "+isItPublished+", foreign coauthors: "+myPub.isForeign_coauthors());
                }
            }


        }catch (SQLException e){
            System.out.println("Unable to select from publications");
        }

    }


}
