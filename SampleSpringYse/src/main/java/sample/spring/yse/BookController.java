package sample.spring.yse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// 브라우저를 비롯한 클라이언트(client)의 요청(request)을 받아서 응답(response)을 만들어내는 컨트롤러(controller) 클래스

@Controller //컨트롤러 어노테이션(Annotation)을 설정
public class BookController {
	// 브라우저 주소가 /create일 때 실행되는 자바 컨트롤러 메소드
	// create 메소드는 브라우저에서 /create 주소가 GET방식으로 입력되었을 때 book/create 경로의 뷰를 보여줌
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	@Autowired // 서비스를 호출하기 위해 BookService를 의존성을 주입
	BookService bookService;
	
	// @RequestMapping :  URL 을 컨트롤러의 메서드와 매핑할 때 사용하는 어노테이션
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map);
	    if (bookId == null) {
	    	//setViewName() : 뷰의 이름
	        mav.setViewName("redirect:/create");
	    }else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  
	    return mav;
	}
	// 책 상세 URL이 입력되면 실행되는 메소드
	// @RequestParam 어노테이션에 의해 쿼리 스트링 파라미터를 읽을 수 있음
	// 스프링은 http 메소드를 구분하지 않고 파라미터를 GET, POST 동일한 방법으로 읽을 수 있게 함
	// Map<key, value>
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.bookService.detail(map);
	    
	 // ModelAndView : 데이터와 뷰를 동시에 설정이 가능, 반환값으로 ModelAndView 객체를 반환
	    ModelAndView mav = new ModelAndView();
	 // addObject() : 뷰로 보낼 데이터 값
	    mav.addObject("data", detailMap);
	 // toString() : 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
}
