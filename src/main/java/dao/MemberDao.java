package dao;

import bean.Member;
import org.apache.log4j.Logger;
import util.QueryExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private final static Logger logger = Logger.getLogger(DatabaseConnector.class);
    static Connection conn = DatabaseConnector.getConnection();

    public static boolean save(Member member) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into member(firstName,lastName,phoneNumber,email,type,address) values(?,?,?,?,?,?)");
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setInt(3, member.getPhoneNumber());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, String.valueOf(member.getType()));
            stmt.setString(6, member.getAddress());
            stmt.execute();
            stmt.close();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public static Member getById(int memberId) {
        try {
            Member member = new Member();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MEMBER where id=?");
            stmt.setInt(1, memberId);

            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                member.setId(res.getInt(1));
                member.setFirstName(res.getString(2));
                member.setLastName(res.getString(3));
                member.setPhoneNumber(res.getInt(4));
                member.setEmail(res.getString(5));
                member.setType(Member.MemberType.stringToEnum(res.getString(6)));
                member.setAddress(res.getString(7));
            }
            res.close();
            stmt.close();
            return member;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public static List<Member> getAllMember() {
        try {
            List<Member> listOfMember = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM MEMBER");
            ResultSet res = QueryExecutor.queryExecute(stmt);
            while (res.next()) {
                Member member = new Member();
                member.setId(res.getInt(1));
                member.setFirstName(res.getString(2));
                member.setLastName(res.getString(3));
                member.setPhoneNumber(res.getInt(4));
                member.setEmail(res.getString(5));
                member.setType(Member.MemberType.stringToEnum(res.getString(6)));
                member.setAddress(res.getString(7));
                listOfMember.add(member);
            }
            res.close();
            stmt.close();
            return listOfMember;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

//    public static void main(String[] args) {
//        Member m=new Member();
//        m.setAddress("niki");
//        m.setEmail("nikiemail");
//        m.setPhoneNumber(909009099);
//        m.setFirstName("nik");
//        m.setLastName("kinj");
//        m.setType(Member.MemberType.SEEKER);
//        save(m);
//      Member m=  getById(1);
//        System.out.println(m.getLastName());
//}
}


