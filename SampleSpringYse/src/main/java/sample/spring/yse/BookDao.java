package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 매퍼 XML을 실행시키는 클래스 DAO(Data Access Object)

@Repository // 외부I/O 처리
public class BookDao {
 @Autowired // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
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
 
//책 수정 데이터베이스에 접속하는 메소드
 public int update(Map<String, Object> map) {
	 return this.sqlSessionTemplate.update("book.update", map);
	 // update 메소드 : update(쿼리ID, 쿼리 파라미터)
	 // 반환값은 영향받은 행
 }
 
//책 삭제 데이터베이스에 접속하는 메소드
 public int delete(Map<String, Object> map) {
	 return this.sqlSessionTemplate.delete("book.delete", map);
 }
 
 // 책 목록 데이터베이스에 접속하는 메소드
 public List<Map<String, Object>> selectList(Map<String, Object> map){
	 return this.sqlSessionTemplate.selectList("book.select_list", map);
 }
}

