import java.util.Scanner;

class Timber {
    char zone;
    int timberID;
    String kind;
    float weight;
    float height;
    int quantity;
    int price;

    // Constructor
    public Timber(char zone, int timberID, String kind, float weight, float height, int quantity, int price) {
        this.zone = zone;
        this.timberID = timberID;
        this.kind = kind;
        this.weight = weight;
        this.height = height;
        this.quantity = quantity;
        this.price = price;
    }

    // Display timber details
    public void display() {
        System.out.println("Zone: " + zone);
        System.out.println("TimberID: " + timberID);
        System.out.println("Kind: " + kind);
        System.out.println("Weight: " + weight);
        System.out.println("Height: " + height);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
    }
}

public class TimberStore {
    static final int MAX_TIMBERS = 100;
    static Timber[] timberArray = new Timber[MAX_TIMBERS];
    static int totalTimbers = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("[1]. Add a new Timber records");
            System.out.println("[2]. Display all records for a given Zone");
            System.out.println("[3]. Display a particular record given the Kind");
            System.out.println("[4]. Analysis of record level");
            System.out.println("[5]. Sales update Report");
            System.out.println("[6]. Delete a particular Timber record");
            System.out.println("[7]. Update a particular Timber record");
            System.out.println("[8]. Exit");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewTimber();
                    break;
                case 2:
                    displayRecordsByZone();
                    break;
                case 3:
                    displayParticularRecordByKind();
                    break;
                case 4:
                    analysisOfRecordLevel();
                    break;
                case 5:
                    salesUpdateReport(scanner);
                    break;
                case 6:
                    deleteRecord(scanner);
                    break;
                case 7:
                    updateRecord(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    static void addNewTimber() {
        Scanner scanner = new Scanner(System.in);
        char zone;
        int timberID;
        String kind;
        float weight;
        float height;
        int quantity;
        int price;

        System.out.print("Enter Zone: ");
        zone = scanner.next().charAt(0);
        System.out.print("Enter TimberID: ");
        timberID = scanner.nextInt();
        System.out.print("Enter Kind: ");
        kind = scanner.next();
        System.out.print("Enter Weight: ");
        weight = scanner.nextFloat();
        System.out.print("Enter Height: ");
        height = scanner.nextFloat();
        System.out.print("Enter Quantity: ");
        quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        price = scanner.nextInt();

        Timber newTimber = new Timber(zone, timberID, kind, weight, height, quantity, price);
        timberArray[totalTimbers++] = newTimber;
        System.out.println("Timber added successfully.");
    }

    static void displayRecordsByZone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Zone to display records: ");
        char zone = scanner.next().charAt(0);

        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].zone == zone) {
                timberArray[i].display();
            }
        }
    }

    static void displayParticularRecordByKind() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Kind to display record: ");
        String kind = scanner.next();

        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].kind.equalsIgnoreCase(kind)) {
                timberArray[i].display();
                return;
            }
        }

        System.out.println("Record not found for the given Kind.");
    }

    static void analysisOfRecordLevel() {
        System.out.println("Records with quantity lower than 100:");
        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].quantity < 100) {
                timberArray[i].display();
            }
        }
    }

    static void salesUpdateReport(Scanner scanner) {
        System.out.print("Enter TimberID to be sold: ");
        int timberID = scanner.nextInt();

        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].timberID == timberID) {
                System.out.print("Enter Quantity to be sold: ");
                int quantitySold = scanner.nextInt();
                if (quantitySold > timberArray[i].quantity) {
                    System.out.println("Error: Quantity to be sold is greater than quantity on hand.");
                    return;
                }
                timberArray[i].quantity -= quantitySold;
                System.out.println("Sales Report:");
                timberArray[i].display();
                return;
            }
        }

        System.out.println("TimberID not found.");
    }

    static void deleteRecord(Scanner scanner) {
        System.out.print("Enter TimberID to be deleted: ");
        int timberID = scanner.nextInt();

        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].timberID == timberID) {
                for (int j = i; j < totalTimbers - 1; j++) {
                    timberArray[j] = timberArray[j + 1];
                }
                totalTimbers--;
                System.out.println("Timber record deleted successfully.");
                return;
            }
        }

        System.out.println("TimberID not found.");
    }

    static void updateRecord(Scanner scanner) {
        System.out.print("Enter TimberID to be updated: ");
        int timberID = scanner.nextInt();

        for (int i = 0; i < totalTimbers; i++) {
            if (timberArray[i].timberID == timberID) {
                System.out.print("Enter new Quantity: ");
                int newQuantity = scanner.nextInt();
                timberArray[i].quantity = newQuantity;
                System.out.println("Timber record updated successfully.");
                return;
            }
        }

        System.out.println("TimberID not found.");
    }
}
