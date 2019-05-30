package service;

import bean.Job;
import bean.Seeker;
import dao.JobDao;
import dao.SeekerDao;

import java.util.List;

public class SeekerService {
public static Seeker getSeekerById(int id){
    Seeker seeker=SeekerDao.getById(id);
    return seeker;
}
public static boolean registerSeeker(Seeker seeker){
    boolean result=SeekerDao.save(seeker);
    return result;
}

public static List<Job> allJobPostedBySeeker(int memberId){
    return JobDao.getJobsPostedBy(memberId);
}

    public static void main(String[] args) {
        List<Job> l=SeekerService.allJobPostedBySeeker(2);
        for(int i=0;i<l.size();i++){
            System.out.println(l.get(i).getTitle());
        }
    }
}
