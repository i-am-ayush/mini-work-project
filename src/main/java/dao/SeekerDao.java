package dao;

import bean.Member;
import bean.Seeker;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeekerDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();
    public static boolean save(Seeker seeker){
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into seeker(memberId,noOfChildren,spouse) values(?,?,?)");
            stmt.setInt(1,seeker.getMemberId());
            stmt.setInt(2,seeker.getNoOfChildren());
            stmt.setString(3,seeker.getSpouseName());
            boolean result=stmt.execute();
            return true;
    } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            return false;
         }
    }
    public static Seeker getById(int seekerId){
        Seeker seeker=new Seeker();
        Member member=MemberDao.getById(seekerId);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seeker where memberId=?");
            stmt.setInt(1, seekerId);
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                seeker.setMemberId(res.getInt(1));
                seeker.setNoOfChildren(res.getInt(2));
                seeker.setSpouseName(res.getString(3));
            }
            seeker.setId(member.getId());
            seeker.setAddress(member.getAddress());
            seeker.setEmail(member.getEmail());
            seeker.setFirstName(member.getFirstName());
            seeker.setLastName(member.getLastName());
            seeker.setPhoneNumber(member.getPhoneNumber());
            seeker.setType(member.getType());
            res.close();
            stmt.close();
            return seeker;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

//    public static void main(String[] args) {
////        Seeker s=new Seeker();
////
////        s.setMemberId(1);
////        s.setNoOfChildren(4);
////        s.setSpouseName("tom");
////        System.out.println(save(s));
//        Seeker s=getById(1);
//        System.out.println(s.getLastName());
//    }
    }

