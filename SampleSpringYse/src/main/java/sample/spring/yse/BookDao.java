package sample.spring.yse;

import java.util.List;
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
 
//å ���� �����ͺ��̽��� �����ϴ� �޼ҵ�
 public int update(Map<String, Object> map) {
	 return this.sqlSessionTemplate.update("book.update", map);
	 // update �޼ҵ� : update(����ID, ���� �Ķ����)
	 // ��ȯ���� ������� ��
 }
 
//å ���� �����ͺ��̽��� �����ϴ� �޼ҵ�
 public int delete(Map<String, Object> map) {
	 return this.sqlSessionTemplate.delete("book.delete", map);
 }
 
 // å ��� �����ͺ��̽��� �����ϴ� �޼ҵ�
 public List<Map<String, Object>> selectList(Map<String, Object> map){
	 return this.sqlSessionTemplate.selectList("book.select_list", map);
 }
}

