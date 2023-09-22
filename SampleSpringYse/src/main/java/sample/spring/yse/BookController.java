package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// �������� ����� Ŭ���̾�Ʈ(client)�� ��û(request)�� �޾Ƽ� ����(response)�� ������ ��Ʈ�ѷ�(controller) Ŭ����

@Controller //��Ʈ�ѷ� ������̼�(Annotation)�� ����
public class BookController {
	// ������ �ּҰ� /create�� �� ����Ǵ� �ڹ� ��Ʈ�ѷ� �޼ҵ�
	// create �޼ҵ�� ���������� /create �ּҰ� GET������� �ԷµǾ��� �� book/create ����� �並 ������
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	@Autowired // ���񽺸� ȣ���ϱ� ���� BookService�� �������� ����
	BookService bookService;
	
	// @RequestMapping :  URL �� ��Ʈ�ѷ��� �޼���� ������ �� ����ϴ� ������̼�
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map);
	    if (bookId == null) {
	    	//setViewName() : ���� �̸�
	        mav.setViewName("redirect:/create");
	    }else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  
	    return mav;
	}
	
	// å �� URL�� �ԷµǸ� ����Ǵ� �޼ҵ�
	// @RequestParam ������̼ǿ� ���� ���� ��Ʈ�� �Ķ���͸� ���� �� ����
	// �������� http �޼ҵ带 �������� �ʰ� �Ķ���͸� GET, POST ������ ������� ���� �� �ְ� ��
	// Map<key, value>
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.bookService.detail(map);
	    
	 // ModelAndView : �����Ϳ� �並 ���ÿ� ������ ����, ��ȯ������ ModelAndView ��ü�� ��ȯ
	    ModelAndView mav = new ModelAndView();
	 // addObject() : ��� ���� ������ ��
	    mav.addObject("data", detailMap);
	 // toString() : ��ü�� ������ �ִ� ������ ������ ���ڿ��� ����� ����
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
	
	// å ���� ȭ��
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
		Map<String, Object> datailMap = this.bookService.detail(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", datailMap);
		mav.setViewName("/book/update");
		return mav;
	}
	
	// å ���� ���
	// å ���� ȭ�鿡�� å ���� ������� �����ִ� �Ķ����
	// 1. GET �Ķ���ͷ� ���޵Ǵ�bookId : /update?bookId=1 
	// 2. form �±׸� ���� ���޵Ǵ� title
	// 3. form �±׸� ���� ���޵Ǵ� category
	// 4. form �±׸� ���� ���޵Ǵ� price
	// http �޼ҵ尡 GET���� POST���� ������� �ʰ� @RequestMapping ������̼��� ������ ������ �Ķ���͸� �־���
	@RequestMapping(value = "update", method = RequestMethod.POST)  
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {  
	ModelAndView mav = new ModelAndView();  

	boolean isUpdateSuccess = this.bookService.edit(map); 
	// ���������� �����Ͱ� ���ŵǾ��� ��� Ȯ���� ���� �� �������� �̵�
	if (isUpdateSuccess) {  
	String bookId = map.get("bookId").toString();  
	mav.setViewName("redirect:/detail?bookId=" + bookId);  
	}else {  
	// ������ �� �Ǿ��� ��� ���� ȭ���� �ٷ� �ٽ� ������
	mav = this.update(map);  
	}  

	return mav;  
	}  
	
	// å ���� ��� ��Ʈ�ѷ� �޼ҵ�
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		// ������ �����ߴ��� Ȯ��
		boolean isDeleteSuccess = this.bookService.remove(map);
		if (isDeleteSuccess) {
			// ������ ���������� �� �������� �����Ƿ� ������� �����̷�Ʈ
			mav.setViewName("redirect:/list");
		} else {
			// ������ ���������� �ٽ� �� �������� �̵�
			String bookId = map.get("bookId").toString();
			mav.setViewName("redirect:/detail?bookId=" + bookId);
		}
		
		return mav;
	}
	
	// å ��� ��Ʈ�ѷ� �޼ҵ�
	@RequestMapping(value = "list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		// å ����� �����ͺ��̽����� ������ ��
		List<Map<String, Object>> list = this.bookService.list(map);
		
		ModelAndView mav = new ModelAndView();
		// �����͸� �信 ������ �� �ְ� mav ��ü�� ����
		mav.addObject("data", list);
		
		// �Ķ���Ͱ� �ִ� �� �˻�
		if(map.containsKey("keyword")) {
			// �Ķ���Ͱ� �ִٸ� �信 keyword�� ����
			mav.addObject("keyword", map.get("keyword"));
		}
		// /book/list �並 ����
		mav.setViewName("/book/list");
		return mav;
	}
}
