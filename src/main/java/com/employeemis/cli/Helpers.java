package com.employeemis.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helpers {

  public static class Prompter {
    public static String get(Scanner scanner) {
      String request = scanner.nextLine();
      enforceExist(request);
      return request;
    }

    public static String getNumber(Scanner scanner) {
      String request = get(scanner);
      if (request.equals("..."))
        return request;
      if (request.matches("^[0-9]+(\\.[0-9]+)*$"))
        return request;
      throw new IllegalArgumentException("Invalid input");
    }
  }

  public static class Printer {
    public static void list(String label, List<String> items) {
      // Show guiding message before printing options
      System.out.println(label);
      System.out.println("-".repeat(label.length()));
      for (int i = 0; i < items.size(); i++)
        System.out.printf("%d. %s%n", i + 1, items.get(i));
    }

    public static void tabular(String title, List<String> columns, List<List<String>> rows) {
      // Calculate max width for each column
      List<Integer> colWidths = new ArrayList<>();
      for (int i = 0; i < columns.size(); i++) {
        int max = columns.get(i).length();
        for (List<String> row : rows) {
          max = Math.max(max, row.get(i).length());
        }
        colWidths.add(max);
      }

      // Print header
      int padding = colWidths.stream().mapToInt(Integer::intValue).sum() + columns.size() * 3 - 1;
      String bar = "+" + "-".repeat(padding) + "+";
      System.out.println(bar);
      System.out.printf(
        "|%s%s%s|%n", " ".repeat((padding - title.length()) / 2), title,
        " ".repeat(Double.valueOf(Math.ceil((padding - title.length()) / 2.0)).intValue()));
      System.out.println(bar);

      // Print column names
      for (int i = 0; i < columns.size(); i++) {
        System.out.printf("| %-" + colWidths.get(i) + "s ", columns.get(i));
      }
      System.out.println("|");

      // Print separator
      for (int width : colWidths) {
        System.out.print("+");
        System.out.print("-".repeat(width + 2));
      }
      System.out.println("+");

      // Print rows
      for (List<String> row : rows) {
        for (int i = 0; i < columns.size(); i++) {
          String cell = i < row.size() ? row.get(i) : "";
          System.out.printf("| %-" + colWidths.get(i) + "s ", cell);
        }
        System.out.println("|");
      }

      // Print bottom bar
      System.out.println(bar);
    }

    public static void important(String message) {
      String x = "+" + "-".repeat(message.length() + 4) + "+";
      System.out.println(x);
      System.out.println("|  " + message + "  |");
      System.out.println(x);
    }
  }

  public static void enforceExist(String request) {
    if (request.equals("exit")) {
      Printer.important("APPLICATION EXISTING");
      System.exit(0);
    }
  }

  public static String select(String label, Scanner prompt, List<String> items) {
    while (true) {
      try {
        // Display available options
        Printer.list(label, items);
        // Now, get user choice
        String choice = Prompter.getNumber(prompt);
        if (choice.equals("..."))
          return choice;
        int selection = Integer.parseInt(choice);
        // Check if given choice is acceptable
        if (selection < 1 || selection > items.size())
          throw new IllegalArgumentException("Invalid choice");
        return choice;
      } catch (Exception e) {
        Printer.important(e.getMessage());
      }
    }
  }
}
