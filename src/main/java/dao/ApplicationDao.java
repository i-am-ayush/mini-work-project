package dao;

import bean.Application;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();

    public static boolean save(Application application) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into job(jobId,memberId,expectedPay) values(?,?,?)");
            stmt.setInt(1, application.getJobId());
            stmt.setInt(2, application.getMemberId());
            stmt.setDouble(3, application.getExpectedPay());
            boolean result = stmt.execute();
            stmt.close();
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return false;
        }

    }

    public static Application getById(int applicationId) {
        Application application = new Application();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM application where id=?");
            stmt.setInt(1, applicationId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                application.setApplicationId(res.getInt(1));
                application.setJobId(res.getInt(2));
                application.setMemberId(res.getInt(3));
                application.setExpectedPay(res.getDouble(4));
                res.close();
                stmt.close();
            }
            return application;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

//    public static void main(String[] args) {
//        Application ap=new Application();
//        ap.setMemberId(1);
//        ap.setJobId(1);
//        ap.setExpectedPay(200);
//        ap.setApplicationId(21);
//        save(ap);
//    }
}
