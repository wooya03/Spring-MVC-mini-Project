package sample.spring.yse;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 매퍼 XML을 실행시키는 클래스 DAO(Data Access Object)

@Repository
public class BookDao {
 @Autowired
 SqlSessionTemplate sqlSessionTemplate;
 // 책 데이터 입력 쿼리를 실행
 public int insert(Map<String, Object> map) {
	  return this.sqlSessionTemplate.insert("book.insert", map);
 }
 public Map<String, Object> selectDetail(Map<String, Object> map){
	 return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	 // sqlSessionTemplate의 selectOne 메소드 : 데이터를 한 개만 가져올 때 사용
	 // 만약 쿼리 결과 행 수가 0개면 selectOne 메소드는 null을 반환하게 됨
	 // 쿼리 결과가 여러 개면 TooManyResultsException 예외를 던짐
 }
}

