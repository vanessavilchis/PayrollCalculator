package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the name of the file employee file to process: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Please Enter the name of the payroll file to create: ");
        String outputFileName = scanner.nextLine();
        try {
            FileReader fileReader = new FileReader("src/main/resources/employees.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;

            FileWriter fileWriter = new FileWriter("src/main/resources/" + outputFileName);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            boolean isJson = outputFileName.endsWith(".json");
            if (isJson) {
                bufWriter.write("[\n");
            } else {
                bufWriter.write("id|name|gross pay\n");
            }
            String input;
            Employee[] employeeList = new Employee[10];
            int count = 0;

            bufWriter.write("id|name|gross pay\n");

            Employee[] employeeList = new Employee[10];
            int count = 0;

            while((input = bufReader.readLine()) != null) {
                String[] tokens = input.split("\\|");
                if (!tokens[0].equals("id")) { //Skipping the header row

                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    double hoursWorked = Double.parseDouble(tokens[2]);
                    double payRate = Double.parseDouble(tokens[3]);

                    employeeList[count] = new Employee(id, name, hoursWorked, payRate);

                    bufWriter.write(String.format("%d|%s|%.2f\n",
                            employeeList[count].getEmployeeId(),
                            employeeList[count].getName(),
                            employeeList[count].getGrossPay()));
                    count++;
                }
            }

            System.out.println(employeeList[0].getName());

            bufReader.close();
            bufWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}