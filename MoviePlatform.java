import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MoviePlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> categories = new ArrayList<>();
        Map<Integer, String> categoryNumbers = new HashMap<>();
        Map<String, List<String>> categoryFilmMap = new HashMap<>();

        boolean running = true;
        while (running) {
            System.out.print("Hoş geldiniz! Sisteme giriş yapmak için 'admin' veya 'client' olarak seçim yapın: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("admin")) {
                adminMode(scanner, categories, categoryNumbers, categoryFilmMap);
            } else if (choice.equalsIgnoreCase("client")) {
                clientMode(scanner, categories, categoryNumbers, categoryFilmMap);
            } else {
                System.out.println("Geçersiz seçim.");
            }

            System.out.println("\n1. Yeniden başlat");
            System.out.println("2. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            String choice2 = scanner.nextLine();

            switch (choice2) {
                case "1":
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim. Program sonlandırılıyor.");
                    running = false;
                    break;
            }
        }

        System.out.println("Program sonlandırıldı.");
    }

    public static void adminMode(Scanner scanner, List<String> categories, Map<Integer, String> categoryNumbers,
                                 Map<String, List<String>> categoryFilmMap) {
        while (true) {
            System.out.println("\n--- Yeni Film Ekleme ---");
            System.out.print("Platformu girin: ");
            String platform = scanner.nextLine();

            System.out.print("Kategoriyi girin: ");
            String category = scanner.nextLine();

            System.out.print("Film adını girin: ");
            String filmName = scanner.nextLine();

            if (!categories.contains(category)) {
                categories.add(category);
                int categoryNumber = categories.size();
                categoryNumbers.put(categoryNumber, category);
                categoryFilmMap.put(category, new ArrayList<>());
            }

            List<String> filmList = categoryFilmMap.get(category);
            filmList.add(platform + " - " + filmName);
            categoryFilmMap.put(category, filmList);

            System.out.print("Film eklemeye devam etmek istiyor musunuz? (Evet/Hayır): ");
            String continueChoice = scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("Hayır")) {
                break;
            }
        }

        System.out.println("\n--- Filmler Başarıyla Kaydedildi ---");
    }

    public static void clientMode(Scanner scanner, List<String> categories, Map<Integer, String> categoryNumbers,
                                  Map<String, List<String>> categoryFilmMap) {
        if (categories.isEmpty()) {
            System.out.println("Henüz hiç film eklenmemiş.");
            return;
        }

        System.out.println("\n--- Kategoriler ---");
        showSortedCategories(categories, categoryNumbers);

        System.out.print("Görmek istediğiniz kategorinin numarasını girin: ");
        int selectedCategoryNumber = scanner.nextInt();
        scanner.nextLine(); 

        String selectedCategory = categoryNumbers.get(selectedCategoryNumber);
        if (selectedCategory != null) {
            List<String> selectedCategoryFilms = categoryFilmMap.get(selectedCategory);

            System.out.println("\n--- " + selectedCategory + " Filmleri ---");
            showFilms(selectedCategoryFilms);
        } else {
            System.out.println("Geçersiz kategori seçimi.");
        }
    }

    public static void showSortedCategories(List<String> categories, Map<Integer, String> categoryNumbers) {
        for (int i = 0; i < categories.size(); i++) {
            int categoryNumber = i + 1;
            String category = categories.get(i);
            categoryNumbers.put(categoryNumber, category);
            System.out.println(categoryNumber + ". " + category);
        }
    }

    public static void showFilms(List<String> filmList) {
        for (String film : filmList) {
            System.out.println("- " + film);
        }
    }
}
