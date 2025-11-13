public class StudentRecord {
    private int roll;
    private String name;
    private int[] marks = new int[3];

    public StudentRecord(int roll, String name, int[] marks) {
        this.roll = roll;
        this.name = (name==null ? "" : name.trim());
        if (marks!=null) for (int i=0;i<3 && i<marks.length;i++) this.marks[i]=marks[i];
    }

    public int getRoll() { return roll; }
    public String getName() { return name; }

    public void validateMarks() throws InvalidMarksException {
        for (int i=0;i<3;i++) {
            if (marks[i] < 0 || marks[i] > 100)
                throw new InvalidMarksException("Invalid marks for subject " + (i+1) + ": " + marks[i]);
        }
    }

    public double average() {
        return (marks[0]+marks[1]+marks[2]) / 3.0;
    }

    public void displayResult() {
        System.out.println("Roll Number: " + roll);
        System.out.println("Student Name: " + name);
        System.out.printf("Marks: %d %d %d%n", marks[0], marks[1], marks[2]);
        System.out.println("Average: " + average());
        System.out.println("Result: " + (average()>=40 ? "Pass" : "Fail"));
    }
}
