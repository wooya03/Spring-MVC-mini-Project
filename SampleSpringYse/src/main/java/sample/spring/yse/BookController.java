package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// �������� ����� Ŭ���̾�Ʈ(client)�� ��û(request)�� �޾Ƽ� ����(response)�� ������ ��Ʈ�ѷ�(controller) Ŭ����

//��Ʈ�ѷ� ������̼�(Annotation)�� ����
@Controller
public class BookController {
	// ������ �ּҰ� /create�� �� ����Ǵ� �ڹ� ��Ʈ�ѷ� �޼ҵ�
	// create �޼ҵ�� ���������� /create �ּҰ� GET������� �ԷµǾ��� �� book/create ����� �並 ������
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	// ���񽺸� ȣ���ϱ� ���� BookService�� �������� ����
	@Autowired
	BookService bookService;
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map);
	    if (bookId == null) {
	        mav.setViewName("redirect:/create");
	    }else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  
	    return mav;
	}
}
