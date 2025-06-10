
import java.util.*;

class Player {
    String name;
    String team;
    int points;

    Player(String name, String team, int points) {
        this.name = name;
        this.team = team;
        this.points = points;
    }

    @Override
    public String toString() {
        return name + " (" + team + ") - Points: " + points;
    }
}

public class Main {
    private static final List<Player> availablePlayers = new ArrayList<>();
    private static final List<Player> selectedTeam = new ArrayList<>();
    private static final int MAX_TEAM_SIZE = 5;

    public static void main(String[] args) {
        initPlayers();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("\n===== Fantasy League App - Dream11 Clone =====");
                System.out.println("1. View Available Players");
                System.out.println("2. Select Player");
                System.out.println("3. View My Team");
                System.out.println("4. Calculate Total Points");
                System.out.println("5. Exit");
                System.out.print("Choose an option (1-5): ");

                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> displayAvailablePlayers();
                    case 2 -> selectPlayer(scanner);
                    case 3 -> displayTeam();
                    case 4 -> calculatePoints();
                    case 5 -> {
                        System.out.println("Thank you for using Fantasy League App. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("❌ Invalid option. Please choose between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and 5.");
            } catch (Exception e) {
                System.out.println("⚠️ An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Initializes the player pool
    private static void initPlayers() {
        availablePlayers.add(new Player("Virat Kohli", "RCB", 85));
        availablePlayers.add(new Player("MS Dhoni", "CSK", 75));
        availablePlayers.add(new Player("Rohit Sharma", "MI", 90));
        availablePlayers.add(new Player("KL Rahul", "LSG", 80));
        availablePlayers.add(new Player("Hardik Pandya", "MI", 70));
        availablePlayers.add(new Player("Shubman Gill", "GT", 88));
    }

    // Shows all available players
    private static void displayAvailablePlayers() {
        System.out.println("\n🏏 Available Players:");
        for (int i = 0; i < availablePlayers.size(); i++) {
            System.out.println((i + 1) + ". " + availablePlayers.get(i));
        }
    }

    // Handles player selection
    private static void selectPlayer(Scanner scanner) {
        if (selectedTeam.size() >= MAX_TEAM_SIZE) {
            System.out.println("⚠️ Your team already has " + MAX_TEAM_SIZE + " players.");
            return;
        }

        displayAvailablePlayers();
        System.out.print("Enter player number to select (1 to " + availablePlayers.size() + "): ");

        try {
            String input = scanner.nextLine().trim();
            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= availablePlayers.size()) {
                System.out.println("❌ Invalid player number.");
                return;
            }

            Player selected = availablePlayers.get(index);
            if (selectedTeam.contains(selected)) {
                System.out.println("⚠️ Player already in your team.");
            } else {
                selectedTeam.add(selected);
                System.out.println("✅ Player added: " + selected.name);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("⚠️ Error selecting player: " + e.getMessage());
        }
    }

    // Displays the current team
    private static void displayTeam() {
        System.out.println("\n📋 Your Fantasy Team:");
        if (selectedTeam.isEmpty()) {
            System.out.println("🚫 No players selected yet.");
        } else {
            for (Player player : selectedTeam) {
                System.out.println("- " + player);
            }
        }
    }

    // Calculates total points for the selected team
    private static void calculatePoints() {
        if (selectedTeam.isEmpty()) {
            System.out.println("🚫 Your team has no players yet.");
            return;
        }

        int total = selectedTeam.stream().mapToInt(p -> p.points).sum();
        System.out.println("\n🎯 Total Fantasy Points: " + total);
    }
}
