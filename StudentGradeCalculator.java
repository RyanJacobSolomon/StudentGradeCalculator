public class StudentGradeCalculator {

    // This is already in VS Code

    public static void main(String[] args) {
        try {
            String fileName = "grades.txt";
            java.io.File file = new java.io.File(fileName);

            // Check if the file exists
            if (!file.exists()) {
                System.out.println("File '" + fileName + "' not found. Creating sample file...");
                createSampleFile(fileName);
                System.out.println("Sample file created successfully!");
            }

            // Calculate the average grade and print student information
            double averageGrade = calculateAverageGrade(fileName);
            System.out.println("\nStudent Grades:");
            printStudentGrades(fileName);
            System.out.printf("Average Grade: %.2f\n", averageGrade);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    // Creates a sample file with student names and grades if it doesn't exist
    public static void createSampleFile(String fileName) throws java.io.IOException {
        java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName));
        writer.println("Alice 85");
        writer.println("Bob 90");
        writer.println("Charlie 75");
        writer.close();
    }

    // Calculates the average grade from the file
    public static double calculateAverageGrade(String fileName) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
        String line;
        double sum = 0;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue; // Skip empty lines

            String[] parts = line.split("\\s+");

            // Skip lines that do not contain valid data or are labeled "Average"
            if (parts.length < 2 || parts[0].equalsIgnoreCase("Average")) continue;

            try {
                double grade = Double.parseDouble(parts[parts.length - 1]);
                sum += grade;
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid grade format in line: " + line);
            }
        }

        reader.close();
        return (count == 0) ? 0.0 : sum / count;
    }

    // Prints each student's name and grade
    public static void printStudentGrades(String fileName) throws java.io.IOException {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split("\\s+");

            if (parts.length < 2 || parts[0].equalsIgnoreCase("Average")) continue;

            StringBuilder name = new StringBuilder();
            for (int i = 0; i < parts.length - 1; i++) {
                name.append(parts[i]);
                if (i < parts.length - 2) name.append(" ");
            }

            String grade = parts[parts.length - 1];
            System.out.println(name + ": " + grade);
        }

        reader.close();
    }
}
