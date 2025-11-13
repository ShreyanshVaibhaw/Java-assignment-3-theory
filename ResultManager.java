import java.util.Scanner;

public class ResultManager {
    private Registry reg = new Registry(50);
    private Scanner sc = new Scanner(System.in);

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String t = sc.nextLine().trim();
            try { return Integer.parseInt(t); }
            catch (NumberFormatException e) { System.out.println("Enter a valid integer."); }
        }
    }

    private void addStudent() {
        try {
            int roll = readInt("Enter Roll Number: ");
            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();
            int[] marks = new int[3];
            for (int i=0;i<3;i++) marks[i]=readInt("Enter marks for subject " + (i+1) + ": ");
            StudentRecord s = new StudentRecord(roll, name, marks);
            reg.add(s);
            System.out.println("Student added successfully.");
        } catch (InvalidMarksException ime) {
            System.out.println("Error: " + ime.getMessage());
        } catch (IllegalArgumentException | IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void showStudent() {
        int roll = readInt("Enter Roll Number to search: ");
        StudentRecord s = reg.findByRoll(roll);
        if (s==null) System.out.println("Not found.");
        else { s.displayResult(); System.out.println("Search completed."); }
    }

    public void menu() {
        try {
            boolean run = true;
            while (run) {
                System.out.println("\n===== Student Result Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. Show Student Details");
                System.out.println("3. List All Students");
                System.out.println("4. Exit");
                System.out.print("Choice: ");
                String c = sc.nextLine().trim();
                switch (c) {
                    case "1": addStudent(); break;
                    case "2": showStudent(); break;
                    case "3": reg.listAll(); break;
                    case "4": run=false; break;
                    default: System.out.println("Invalid option.");
                }
            }
        } finally {
            sc.close();
            System.out.println("Scanner closed. Program terminated.");
        }
    }

    public static void main(String[] args) {
        new ResultManager().menu();
    }
}
