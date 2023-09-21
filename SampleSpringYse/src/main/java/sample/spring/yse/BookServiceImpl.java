package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스 클래스 : 컨트롤러와 DAO를 연결


@Service // 로직 처리
public class BookServiceImpl implements BookService {
 @Autowired
 BookDao bookDao;
 
 @Override // 상위 인터페이스에 정의된 것을 재정의
 public String create(Map<String, Object> map) {
     int affectRowCount = this.bookDao.insert(map);
     if (affectRowCount ==  1) {
         return map.get("book_id").toString();
     }
     return null;
 }
 @Override
 // DAO를 호출한 결과를 바로 리턴
 public Map<String, Object> detail(Map<String, Object> map){
	    return this.bookDao.selectDetail(map);
	}
}