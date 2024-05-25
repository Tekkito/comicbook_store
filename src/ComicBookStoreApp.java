import java.util.Scanner;

public class ComicBookStoreApp {
    private static ComicBookStore store;
    private static User currentUser;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        store = ComicBookStore.loadFromFile("store.dat");

        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (authenticateUser(username, password)) {
            System.out.println("Добро пожаловать, " + username + "!");
            runApp();
        } else {
            System.out.println("Неверный логин или пароль.");
        }

        store.saveToFile("store.dat");
    }

    private static boolean authenticateUser(String username, String password) {
        currentUser = new User("admin", "admin");
        return currentUser.getUsername().equals(username) && currentUser.authenticate(password);
    }

    private static void runApp() {
        while (true) {
            System.out.println("\n1. Просмотр комиксов");
            System.out.println("2. Добавить комикс");
            System.out.println("3. Удалить комикс");
            System.out.println("4. Редактировать комикс");
            System.out.println("5. Продать комикс");
            System.out.println("6. Скидка на комикс");
            System.out.println("7. Отложить комикс");
            System.out.println("8. Поиск комиксов");
            System.out.println("9. Выход");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    store.viewComics();
                    break;
                case 2:
                    addComic();
                    break;
                case 3:
                    System.out.print("Введите название комикса для удаления: ");
                    String titleToRemove = scanner.nextLine();
                    store.removeComic(titleToRemove);
                    break;
                case 4:
                    editComic();
                    break;
                case 5:
                    System.out.print("Введите название комикса для продажи: ");
                    String titleToSell = scanner.nextLine();
                    store.sellComic(titleToSell);
                    break;
                case 6:
                    applyDiscount();
                    break;
                case 7:
                    reserveComic();
                    break;
                case 8:
                    searchComics();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Неверная опция. Попробуйте снова.");
            }
        }
    }

    private static void addComic() {
        System.out.print("Введите название: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора: ");
        String author = scanner.nextLine();
        System.out.print("Введите издательство: ");
        String publisher = scanner.nextLine();
        System.out.print("Введите количество страниц: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите жанр: ");
        String genre = scanner.nextLine();
        System.out.print("Введите год издания: ");
        int year = scanner.nextInt();
        System.out.print("Введите себестоимость: ");
        double costPrice = scanner.nextDouble();
        System.out.print("Введите цену для продажи: ");
        double salePrice = scanner.nextDouble();
        System.out.print("Является ли комикс продолжением (true/false): ");
        boolean isSequel = scanner.nextBoolean();
        System.out.print("Введите количество в наличии: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        ComicBook comic = new ComicBook(title, author, publisher, pages, genre, year, costPrice, salePrice, isSequel, stock);
        store.addComic(comic);
    }

    private static void editComic() {
        System.out.print("Введите название комикса для редактирования: ");
        String title = scanner.nextLine();
        System.out.print("Введите новое название: ");
        String newTitle = scanner.nextLine();
        System.out.print("Введите нового автора: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Введите новое издательство: ");
        String newPublisher = scanner.nextLine();
        System.out.print("Введите новое количество страниц: ");
        int newPages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите новый жанр: ");
        String newGenre = scanner.nextLine();
        System.out.print("Введите новый год издания: ");
        int newYear = scanner.nextInt();
        System.out.print("Введите новую себестоимость: ");
        double newCostPrice = scanner.nextDouble();
        System.out.print("Введите новую цену для продажи: ");
        double newSalePrice = scanner.nextDouble();
        System.out.print("Является ли комикс продолжением (true/false): ");
        boolean newIsSequel = scanner.nextBoolean();
        System.out.print("Введите новое количество в наличии: ");
        int newStock = scanner.nextInt();
        scanner.nextLine();

        ComicBook updatedComic = new ComicBook(newTitle, newAuthor, newPublisher, newPages, newGenre, newYear, newCostPrice, newSalePrice, newIsSequel, newStock);
        store.editComic(title, updatedComic);
    }

    private static void applyDiscount() {
        System.out.print("Введите название комикса для скидки: ");
        String title = scanner.nextLine();
        System.out.print("Введите процент скидки: ");
        double discountPercentage = scanner.nextDouble();
        scanner.nextLine();
        store.discountComic(title, discountPercentage);
    }

    private static void reserveComic() {
        System.out.print("Введите название комикса для отложения: ");
        String title = scanner.nextLine();
        System.out.print("Введите имя клиента: ");
        String customer = scanner.nextLine();
        store.reserveComic(title, customer);
    }

    private static void searchComics() {
        System.out.print("Введите параметр поиска (title/author/genre): ");
        String parameter = scanner.nextLine();
        System.out.print("Введите значение для поиска: ");
        String value = scanner.nextLine();
        store.searchComic(parameter, value);
    }
}
