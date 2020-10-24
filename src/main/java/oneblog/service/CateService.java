package oneblog.service;

import oneblog.model.Category;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 16:33
 */
public interface CateService {
    int addCate(Category category);
    int deleteCate(Integer cateId);
    Category getCateById(Integer cateId);
    List<Category> list();
    Boolean cateExist(String cateName);
    int count();
}
