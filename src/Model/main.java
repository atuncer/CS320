package Model;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
public class main {

    public static void main(String[] args) throws IOException {
        DBConnection database_connection = null;
        instantiateSQLite();
        String url = readConfig(database_connection);
        try {
            database_connection = new DBConnection(url);
            processCommand(database_connection);
            run(database_connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database_connection != null) {
                database_connection.close();
            }
        }

    }
    /**
     Serverın adresi returnliycek
     @param database_connection mysqlde lazım olcak
     @return databasein adresi
     */
    public static String readConfig(DBConnection database_connection){
        return "jdbc:sqlite:db1.db";
    }

    /**
     Adından belli zaten
     */
    public static void instantiateSQLite() {
        String dbLocation = "db1.db";
        String sqliteURL = "jdbc:sqlite:" + dbLocation;

        java.sql.Connection conn = null;
        try {
            conn = java.sql.DriverManager.getConnection(sqliteURL);
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    /**
     * eğer SQLite db'si yoksa bir tane oluşturuyor
     * @param connection mainden aktif connectionu çekip burda kullanıyor
     * @throws SQLException
     */
    public static void processCommand(DBConnection connection) throws  SQLException {
        Scanner scn = new Scanner(System.in);
        connection.send_update(
    "CREATE TABLE IF NOT EXISTS \"Users\" (\n" +
            "\t\"userName\"\tvarchar(255) NOT NULL,\n" +
            "\t\"password\"\tvarchar(255) NOT NULL,\n" +
            "\t\"Role\"\tINT,\n" +
            "\tPRIMARY KEY(\"userName\")\n" +
            "CHECK ((Role > 0) AND (role < 4))"+
            ");" +
            "CREATE TABLE IF NOT EXISTS \"Products\" (\n" +
            "\t\"name\"\tvarchar(255) NOT NULL,\n" +
            "\t\"count\"\tint,\n" +
            "\tPRIMARY KEY(\"name\")\n" +
            ");"
        );
    }

    /**
     * Commandline'dan test etmek için lazım, GUIde gerek olmayacak gibi
     * @param conn mainden connection çekiyo
     * @throws SQLException
     */
    public static void run(DBConnection conn) throws SQLException {
        //conn.send_update("INSERT INTO Users (username,password,role)" + "VALUES(\"a\",1,1);");
        System.out.println("Username: ");
        String userName;
        String pass;
        Scanner S = new Scanner(System.in);
        userName = S.next();

        ResultSet resultSet = conn.send_query("SELECT * from Users where username='"+ userName +"'");
        if (!resultSet.isBeforeFirst() ) {  System.out.println("No data"); return; }

        else {
            System.out.println("Password: ");
            pass = S.next();

            if (pass.equals(ReturnPass(conn.send_query("SELECT password from Users where username='"+ userName +"'")))){
                System.out.println("Logged in");

                if (conn.send_query("SELECT Role from Users where username='"+ userName +"'").getString(1).equals("1")){

                    System.out.println("Hello admin");
                    Admin user = new Admin();
                    user.addProduct(conn,"prod",5);
                    user.checkProduct(conn,"prod");
                    user.sellProduct(conn,"prod",24);
                    //user.addUser(conn,"atahan4","123",2);
                    user.deleteUser(conn,"atahan4");
                }

                else if (conn.send_query("SELECT Role from Users where username='"+ userName +"'").getString(1).equals("2")){
                    System.out.println("Hello Manager");
                    Manager user = new Manager();
                }

                else{ System.out.println("Hello Salesman");
                    Salesman user = new Salesman();
                }
            }
            else System.out.println("Wrong Password");
        }
    }

    public static String ReturnPass(ResultSet resultSet) throws SQLException {
        if (!resultSet.isBeforeFirst()) {  return null; }
        else {
            return resultSet.getString(1);
        }
    }
}
