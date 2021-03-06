package dao;

import bean.Job;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class JobDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();

    public static boolean save(Job job) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into job(title,postedBy,startDateTime,endDateTime,payPerHour) values(?,?,?,?,?)");
            stmt.setString(1, job.getTitle());
            stmt.setInt(2, job.getPostedBy());
            java.sql.Date sqlDate1 = new java.sql.Date(job.getStartDateTime().getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(job.getEndDateTime().getTime());
            stmt.setDate(3, sqlDate1);
            stmt.setDate(4, sqlDate2);
            stmt.setDouble(5, job.getPayPerHour());
            boolean result = stmt.execute();
            stmt.close();
            return result;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);

            return false;
        }
    }
    public static Job getById(int jobId){
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM JOB  where id=?");
            stmt.setInt(1,jobId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            Job job=new Job();
            while (res.next()) {
                job.setId(res.getInt(1));
                job.setTitle(res.getString(2));
                job.setPostedBy(res.getInt(3));
                job.setStartDateTime(res.getDate(4));
                job.setEndDateTime(res.getDate(5));
                job.setPayPerHour(res.getDouble(6));
            }
            res.close();
            stmt.close();
            return job;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
////        Job job=new Job();
////        job.setPayPerHour(100.0);
////        Date date=new Date();
////        job.setStartDateTime(date);
////        job.setEndDateTime(date);
////        job.setPostedBy(1);
////        job.setTitle("job search");
////        System.out.println(save(job));
//        Job job=getById(1);
//        System.out.println(job.getStartDateTime());
//    }
}

