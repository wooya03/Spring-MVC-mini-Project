package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ���� ũ���� : ��Ʈ�ѷ��� DAO�� ����

@Service
public class BookServiceImpl implements BookService {
 @Autowired
 BookDao bookDao;
 
 @Override // ���� �������̽��� ���ǵ� ���� ������
 public String create(Map<String, Object> map) {
     int affectRowCount = this.bookDao.insert(map);
     if (affectRowCount ==  1) {
         return map.get("book_id").toString();
     }
     return null;

 }
}