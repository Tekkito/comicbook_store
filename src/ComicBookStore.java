import java.io.*;
import java.util.*;

public class ComicBookStore implements Serializable {
    private List<ComicBook> inventory;
    private Map<String, Double> discounts;
    private Map<String, Integer> reservedComics;

    public ComicBookStore() {
        inventory = new ArrayList<>();
        discounts = new HashMap<>();
        reservedComics = new HashMap<>();
    }

    public void addComic(ComicBook comic) {
        inventory.add(comic);
        System.out.println("Комикс \"" + comic.getTitle() + "\" добавлен в магазин.");
    }

    public void removeComic(String title) {
        inventory.removeIf(comic -> comic.getTitle().equalsIgnoreCase(title));
        System.out.println("Комикс \"" + title + "\" удален из магазина.");
    }

    public void editComic(String title, ComicBook updatedComic) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getTitle().equalsIgnoreCase(title)) {
                inventory.set(i, updatedComic);
                System.out.println("Комикс \"" + title + "\" обновлен.");
                return;
            }
        }
        System.out.println("Комикс \"" + title + "\" не найден.");
    }

    public void sellComic(String title) {
        for (ComicBook comic : inventory) {
            if (comic.getTitle().equalsIgnoreCase(title) && comic.getStock() > 0) {
                comic.setStock(comic.getStock() - 1);
                System.out.println("Вы купили \"" + comic.getTitle() + "\". Осталось в наличии: " + comic.getStock() + " шт.");
                return;
            }
        }
        System.out.println("Комикс \"" + title + "\" не найден или распродан.");
    }

    public void discountComic(String title, double discountPercentage) {
        discounts.put(title, discountPercentage);
        System.out.println("Комикс \"" + title + "\" теперь с скидкой " + discountPercentage + "%.");
    }

    public void reserveComic(String title, String customer) {
        reservedComics.put(title, reservedComics.getOrDefault(title, 0) + 1);
        System.out.println("Комикс \"" + title + "\" отложен для " + customer + ".");
    }

    public void viewComics() {
        for (ComicBook comic : inventory) {
            System.out.println(comic);
        }
    }

    public void searchComic(String parameter, String value) {
        for (ComicBook comic : inventory) {
            if ((parameter.equalsIgnoreCase("title") && comic.getTitle().equalsIgnoreCase(value)) ||
                    (parameter.equalsIgnoreCase("author") && comic.getAuthor().equalsIgnoreCase(value)) ||
                    (parameter.equalsIgnoreCase("genre") && comic.getGenre().equalsIgnoreCase(value))) {
                System.out.println(comic);
            }
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ComicBookStore loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ComicBookStore) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ComicBookStore();
        }
    }
}
