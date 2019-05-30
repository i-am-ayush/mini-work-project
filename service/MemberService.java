package service;

import bean.Member;
import dao.MemberDao;

public class MemberService {
    public static boolean authentication(String emailId, String password) {
        Member m=MemberDao.getByEmailIdAndPassword(emailId, password);
        if(m!=null){
            return true;
        }
        else{
            return false;
        }

    }


    public static void main(String[] args) {
        System.out.println(MemberService.authentication("harsh@gmail.com","harsh123"));
        System.out.println(MemberService.authentication("ram@gmail.com","ram123"));


    }
    public static  int getMemberIdByEmail(String emailId){
        Member member=MemberDao.getByEmailId(emailId);
        return member.getId();
    }
    public static Member getMemberById(int id){
        Member member=MemberDao.getById(id);
        return member;
    }

}