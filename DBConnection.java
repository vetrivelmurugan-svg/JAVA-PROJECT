
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/kec_eval_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DEFAULT_USER = "root";

    // Primary password configured for this environment
    private static final String DEFAULT_PASSWORD = "Vetri@1234";

    // Fallback passwords to support multiple developer setups seamlessly
    private static final String[] FALLBACK_PASSWORDS = {"Vetri@1234", "root", "", "admin", "password", "1234", "123456"};

    private static String verifiedPassword = null;

    public static Connection getConnection() throws SQLException {
        String envUrl = System.getenv("DB_URL");
        String envUser = System.getenv("DB_USER");
        String envPass = System.getenv("DB_PASSWORD");

        String url = (envUrl != null && !envUrl.trim().isEmpty()) ? envUrl : DEFAULT_URL;
        String user = (envUser != null && !envUser.trim().isEmpty()) ? envUser : DEFAULT_USER;

        if (envPass != null) {
            return DriverManager.getConnection(url, user, envPass);
        }

        if (verifiedPassword != null) {
            return DriverManager.getConnection(url, user, verifiedPassword);
        }

        // Try primary password first
        try {
            Connection conn = DriverManager.getConnection(url, user, DEFAULT_PASSWORD);
            verifiedPassword = DEFAULT_PASSWORD;
            return conn;
        } catch (SQLException e) {
            // Try fallback passwords
            for (String pwd : FALLBACK_PASSWORDS) {
                try {
                    Connection conn = DriverManager.getConnection(url, user, pwd);
                    verifiedPassword = pwd;
                    return conn;
                } catch (SQLException ignored) {
                }
            }
            throw e;
        }
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("[SUCCESS] Successfully connected to MySQL database: " + conn.getCatalog());
            System.out.println("  Database Product: " + conn.getMetaData().getDatabaseProductName() + " " + conn.getMetaData().getDatabaseProductVersion());
            System.out.println("  Driver Version  : " + conn.getMetaData().getDriverVersion());
        } catch (SQLException e) {
            System.out.println("[ERROR] Database connection failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        testConnection();
    }
}

