package src;

import java.sql.*;
import java.util.Scanner;

public class SQL_app {
    public static void main(String[] args) {
        // Pobieranie loginu i hasła od użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        while (true) {
            // Połączenie z bazą danych
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:sqlserver://serwer-brentowo:1433;databaseName=PatrykRomanowski;encrypt=true;trustServerCertificate=true;",
                    login, password)) {
                System.out.println("Połączenie z bazą danych zostało ustanowione.");

                // Pobieranie danych od użytkownika
                System.out.print("Podaj imię: ");
                String imiona = scanner.nextLine();
                System.out.print("Podaj nazwisko: ");
                String nazwisko = scanner.nextLine();

                // Wykonanie zapytania SQL i wyświetlenie wyników
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(
                            "SELECT imiona, nazwisko, pesel, Indeks_Studenta " +
                                    "FROM dbo.Osoba " +
                                    "WHERE imiona = '" + imiona + "' " +
                                    "AND nazwisko = '" + nazwisko + "' ");
                    while (rs.next()) {
                        imiona = rs.getString("imiona");
                        nazwisko = rs.getString("nazwisko");
                        String pesel = rs.getString("PESEL");
                        String indeks = rs.getString("Indeks_Studenta");
                        System.out.println("Imię studenta : " + imiona + "\n" +
                                " " + "Nazwisko studenta : " + nazwisko + "\n" +
                                " " + "Pesel : " + pesel + "\n" +
                                " " + "Indeks : " + indeks + "\n");
                    }

                } catch (SQLException e) {
                    System.out.println("Wystąpił błąd podczas wykonywania zapytania SQL: " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Wystąpił błąd podczas łączenia z bazą danych: " + e.getMessage());
            }

        }

    }
}




//Uwaga: w powyższym przykładzie należy zamienić <server>, <port> i <database> na odpowiednie wartości (np. nazwę serwera, numer portu i nazwę bazy danych).



/*
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Pobieranie loginu i hasła od użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj login: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        // Połączenie z bazą danych
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://<server>:<port>;databaseName=<database>",
                login, password)) {
            System.out.println("Połączenie z bazą danych zostało ustanowione.");

            // Pobieranie danych od użytkownika
            System.out.print("Podaj imię: ");
            String firstName = scanner.nextLine();
            System.out.print("Podaj nazwisko: ");
            String lastName = scanner.nextLine();

            // Wykonanie zapytania SQL i wyświetlenie wyników
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT first_name, last_name, phone_number " +
                                "FROM users " +
                                "WHERE first_name = '" + firstName + "' " +
                                "AND last_name = '" + lastName + "'");
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String phoneNumber = rs.getString("phone_number");
                    System.out.println(firstName + " " + lastName + ": " + phoneNumber);
                }
            } catch (SQLException e) {
                System.out.println("Wystąpił błąd podczas wykonywania zapytania SQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas łączenia z bazą danych: " + e.getMessage());
        }
    }*/
