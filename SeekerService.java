package service;

import bean.Seeker;
import dao.SeekerDao;

public class SeekerService {
public static Seeker getSeekerById(int id){
    Seeker seeker=SeekerDao.getById(id);
    return seeker;
}
public static boolean registerSeeker(Seeker seeker){
    boolean result=SeekerDao.save(seeker);
    return result;
}
}
