package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ���� Ŭ���� : ��Ʈ�ѷ��� DAO�� ����


@Service // ���� ó��
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
 @Override
 // DAO�� ȣ���� ����� �ٷ� ����
 public Map<String, Object> detail(Map<String, Object> map){
	    return this.bookDao.selectDetail(map);
	}
}