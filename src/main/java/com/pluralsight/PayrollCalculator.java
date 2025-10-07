package com.pluralsight;

import java.io.*;

public class PayrollCalculator {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/main/resources/employees.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;
            System.out.println("Employee ID | Name              | Gross Pay");
            while ((input = bufReader.readLine()) != null) {
                String[] tokens = input.split("\\|");

                int employeeId = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);

                System.out.printf("%11d | %-17s | $%,9.2f%n",
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getGrossPay());
            }
        } catch (IOException e) {e.printStackTrace();
        }
    }
}

