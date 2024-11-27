import java.util.*;
// MarkProcess class to handle filtering and sorting
class MarkProcess {
    public static List<Student> filterStudents(List<Student> students, double threshold) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getTotal() < threshold) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public static List<Student> sortStudents(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getTotal() > students.get(j + 1).getTotal()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
        return students;
    }
}