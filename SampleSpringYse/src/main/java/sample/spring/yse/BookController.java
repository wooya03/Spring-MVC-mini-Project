package sample.spring.yse;

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
}
