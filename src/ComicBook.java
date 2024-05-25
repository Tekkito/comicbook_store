import java.io.Serializable;

public class ComicBook implements Serializable {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSequel() {
        return isSequel;
    }

    public void setSequel(boolean sequel) {
        isSequel = sequel;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    private String title;
    private String author;
    private String publisher;
    private int pages;
    private String genre;
    private int year;
    private double costPrice;
    private double salePrice;
    private boolean isSequel;
    private int stock;

    public ComicBook(String title, String author, String publisher, int pages, String genre, int year, double costPrice, double salePrice, boolean isSequel, int stock) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.genre = genre;
        this.year = year;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.isSequel = isSequel;
        this.stock = stock;
    }


    @Override
    public String toString() {
        return String.format("Название: %s, Автор: %s, Издательство: %s, Кол-во страниц: %d, Жанр: %s, Год издания: %d, Цена: %.2f руб., В наличии: %d шт.",
                title, author, publisher, pages, genre, year, salePrice, stock);
    }
}
