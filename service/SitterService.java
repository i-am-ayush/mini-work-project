package service;

import bean.Sitter;
import dao.SitterDao;

public class SitterService {
    public static Sitter getSitterById(int id){
        Sitter sitter= SitterDao.getById(id);
        return sitter;
    }
}
