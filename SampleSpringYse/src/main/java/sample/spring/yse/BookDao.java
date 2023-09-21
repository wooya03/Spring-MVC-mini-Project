package sample.spring.yse;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// ���� XML�� �����Ű�� Ŭ���� DAO(Data Access Object)

@Repository // �ܺ�I/O ó��
public class BookDao {
 @Autowired // �ʿ��� ���� ��ü�� ��Ÿ��"�� �ش��ϴ� ���� ã�� ����
 SqlSessionTemplate sqlSessionTemplate;
 // å ������ �Է� ������ ����
 public int insert(Map<String, Object> map) {
	  return this.sqlSessionTemplate.insert("book.insert", map);
 }
 public Map<String, Object> selectDetail(Map<String, Object> map){
	 return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	 // sqlSessionTemplate�� selectOne �޼ҵ� : �����͸� �� ���� ������ �� ���
	 // ���� ���� ��� �� ���� 0���� selectOne �޼ҵ�� null�� ��ȯ�ϰ� ��
	 // ���� ����� ���� ���� TooManyResultsException ���ܸ� ����
 }
}

