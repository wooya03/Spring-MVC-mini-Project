package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// 브라우저를 비롯한 클라이언트(client)의 요청(request)을 받아서 응답(response)을 만들어내는 컨트롤러(controller) 클래스

//컨트롤러 어노테이션(Annotation)을 설정
@Controller
public class BookController {
	// 브라우저 주소가 /create일 때 실행되는 자바 컨트롤러 메소드
	// create 메소드는 브라우저에서 /create 주소가 GET방식으로 입력되었을 때 book/create 경로의 뷰를 보여줌
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	// 서비스를 호출하기 위해 BookService를 의존성을 주입
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
