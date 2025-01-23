package models.BookStore;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BooksResponse{

    @JsonProperty("books")
    private List<BooksItem> books;

    public void setBooks(List<BooksItem> books){
        this.books = books;
    }

    public List<BooksItem> getBooks(){
        return books;
    }

    @Override
    public String toString() {
        return "BooksResponse{" +
                "books=" + books +
                '}';
    }

}

