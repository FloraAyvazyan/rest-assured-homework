package models.BookStore;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooksItem{

    @JsonProperty("website")
    private String website;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("subTitle")
    private String subTitle;

    @JsonProperty("author")
    private String author;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("description")
    private String description;

    @JsonProperty("title")
    private String title;

    @JsonProperty("publish_date")
    private String publishDate;

    public void setWebsite(String website){
        this.website = website;
    }

    public String getWebsite(){
        return website;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public int getPages(){
        return pages;
    }

    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public String getPublishDate(){
        return publishDate;
    }

    @Override
    public String toString() {
        return "BooksItem{" +
                "website='" + website + '\'' +
                ", pages=" + pages +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}