public class Registry {
    private StudentRecord[] students;
    private int size = 0;

    public Registry(int capacity) {
        students = new StudentRecord[Math.max(5, capacity)];
    }

    public Registry() { this(50); }

    public void add(StudentRecord s) throws InvalidMarksException {
        if (s==null) throw new IllegalArgumentException("Null student");
        if (findIndex(s.getRoll())!=-1) throw new IllegalArgumentException("Roll exists");
        s.validateMarks();
        if (size>=students.length) throw new IllegalStateException("Registry full");
        students[size++] = s;
    }

    public StudentRecord findByRoll(int roll) {
        int idx = findIndex(roll);
        return idx==-1 ? null : students[idx];
    }

    private int findIndex(int roll) {
        for (int i=0;i<size;i++) if (students[i].getRoll()==roll) return i;
        return -1;
    }

    public void listAll() {
        if (size==0) { System.out.println("No records."); return; }
        for (int i=0;i<size;i++) {
            StudentRecord s = students[i];
            System.out.printf("%d) Roll:%d Name:%s Avg:%.2f%n", i+1, s.getRoll(), s.getName(), s.average());
        }
    }
}
