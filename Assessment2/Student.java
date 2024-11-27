import java.util.Arrays;

class Student {
    private String name;
    private String studentId;
    private double[] marks;
    private double total;

    public Student(String name, String studentId, double[] marks) {
        this.name = name;
        this.studentId = studentId;
        this.marks = marks;
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): Marks=%s, Total=%.2f",
                name, studentId, Arrays.toString(marks), total);
    }
}
