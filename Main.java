import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> records = new ArrayList<>();
    static final String FILE_NAME = "crops.txt";
    static String language = "English";

    public static void main(String[] args) {
        loadRecords();

        System.out.println("==================================");
        System.out.println("       AGRI RESCUE AI");
        System.out.println(" Smart Agriculture Assistant");
        System.out.println("==================================");

        System.out.println("Choose language / Bhasha enchukondi:");
        System.out.println("1. English");
        System.out.println("2. Telugu");
        System.out.print("Enter choice: ");

        language = readLine();
        language = language.equals("2") ? "Telugu" : "English";

        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Farmer and Crop");
            System.out.println("2. Check Crop Symptoms");
            System.out.println("3. Irrigation Advice");
            System.out.println("4. View Saved Records");
            System.out.println("5. Agriculture Tips");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = readLine();

            switch (choice) {
                case "1":
                    addCrop();
                    break;
                case "2":
                    checkSymptoms();
                    break;
                case "3":
                    irrigationAdvice();
                    break;
                case "4":
                    viewRecords();
                    break;
                case "5":
                    agricultureTips();
                    break;
                case "6":
                    saveRecords();
                    System.out.println("Thank you for using Agri Rescue AI!");
                    return;
                default:
                    System.out.println("Invalid choice. Enter 1 to 6.");
            }
        }
    }

    static String readLine() {
        return sc.nextLine().trim();
    }

    static void addCrop() {
        System.out.println("\n--- ADD FARMER AND CROP ---");

        System.out.print("Farmer name: ");
        String farmer = readLine();

        System.out.print("Crop name: ");
        String crop = readLine();

        System.out.print("Soil condition (dry/moist/wet): ");
        String soil = readLine();

        if (farmer.isEmpty() || crop.isEmpty() || soil.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        String record = farmer + "|" + crop + "|" + soil;
        records.add(record);

        if (saveRecords()) {
            System.out.println("Crop record saved successfully!");
        } else {
            System.out.println(
                "Record added for this session, but file saving failed.");
        }
    }

    static void checkSymptoms() {
        System.out.println("\n--- CROP SYMPTOM CHECKER ---");

        System.out.print("Enter crop name: ");
        String crop = readLine();

        if (crop.isEmpty()) {
            System.out.println("Crop name is required.");
            return;
        }

        System.out.println("Select the observed symptom:");
        System.out.println("1. Yellow leaves");
        System.out.println("2. Brown or black spots");
        System.out.println("3. Wilting");
        System.out.println("4. White powder on leaves");
        System.out.println("5. Holes in leaves");
        System.out.println("6. No visible symptoms");
        System.out.print("Choice: ");

        String choice = readLine();
        String advice;

        switch (choice) {
            case "1":
                advice = "Possible causes include nutrient deficiency, "
                       + "overwatering or poor drainage. Check soil moisture.";
                break;
            case "2":
                advice = "Possible disease or plant stress. Inspect affected "
                       + "leaves and consult an agricultural expert.";
                break;
            case "3":
                advice = "Check soil moisture, roots and drainage. "
                       + "Both too little and too much water can cause wilting.";
                break;
            case "4":
                advice = "Possible powdery mildew or another leaf problem. "
                       + "Confirm the cause before applying treatment.";
                break;
            case "5":
                advice = "Possible insect damage. Inspect both sides "
                       + "of leaves for insects or eggs.";
                break;
            case "6":
                advice = "No symptoms reported. Monitor the crop regularly.";
                break;
            default:
                System.out.println("Invalid symptom choice.");
                return;
        }

        System.out.println("\nCrop: " + crop);
        System.out.println("Advice: " + advice);

        if (language.equals("Telugu")) {
            System.out.println("గమనిక: ఇవి ప్రాథమిక సూచనలు మాత్రమే.");
        } else {
            System.out.println(
                "Note: This is a rule-based suggestion, not a confirmed diagnosis.");
        }
    }

    static void irrigationAdvice() {
        System.out.println("\n--- IRRIGATION ADVISOR ---");

        System.out.print("Enter crop name: ");
        String crop = readLine();

        System.out.print("Enter soil condition (dry/moist/wet): ");
        String soil = readLine().toLowerCase(Locale.ROOT);

        if (crop.isEmpty() || soil.isEmpty()) {
            System.out.println("Please enter both details.");
            return;
        }

        System.out.println("Crop: " + crop);

        if (soil.contains("dry")) {
            System.out.println(
                "Advice: Check root-zone moisture. Water if required "
                + "for the crop and its growth stage.");
        } else if (soil.contains("moist") || soil.contains("wet")) {
            System.out.println(
                "Advice: Check moisture before watering again. "
                + "Avoid waterlogging.");
        } else {
            System.out.println(
                "Advice: Use crop-specific guidance, recent rainfall "
                + "and a soil moisture check before irrigation.");
        }
    }

    static void viewRecords() {
        System.out.println("\n--- SAVED CROP RECORDS ---");

        if (records.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        for (int i = 0; i < records.size(); i++) {
            String[] parts = records.get(i).split("\\|", -1);

            if (parts.length == 3) {
                System.out.println("\nRecord " + (i + 1));
                System.out.println("Farmer: " + parts[0]);
                System.out.println("Crop: " + parts[1]);
                System.out.println("Soil: " + parts[2]);
            }
        }
    }

    static void agricultureTips() {
        System.out.println("\n--- AGRICULTURE TIPS ---");
        System.out.println("1. Check soil moisture before watering.");
        System.out.println("2. Inspect leaves regularly for unusual symptoms.");
        System.out.println("3. Maintain proper field drainage.");
        System.out.println("4. Use fertilizers based on soil testing.");
        System.out.println("5. Confirm disease and pest problems before treatment.");
        System.out.println("6. Follow locally recommended crop practices.");
    }

    static boolean saveRecords() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static void loadRecords() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                if (parts.length == 3) {
                    records.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Previous records could not be loaded.");
        }
    }
}
