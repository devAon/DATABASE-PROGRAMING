package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.board_freshmanot.*;
import controller.board_messenger.*;
import controller.board_notice.*;
import controller.club.*;
import controller.customer.*;
import controller.littlemeeting.*;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	// �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
		mappings.put("/", new ForwardController("index.jsp"));

		mappings.put("/customer/login/form", new ForwardController("/customer/loginForm.jsp"));
		mappings.put("/customer/login", new LoginController());
		mappings.put("/customer/logout", new LogoutController());
		mappings.put("/customer/list", new ListCustomerController());
		mappings.put("/customer/view", new ViewCustomerController());
		mappings.put("/customer/register/form", new ForwardController("/customer/registerForm.jsp"));
		mappings.put("/customer/register", new RegisterCustomerController());
		mappings.put("/customer/update/form", new UpdateCustomerFormController());
		mappings.put("/customer/update", new UpdateCustomerController());
		mappings.put("/customer/delete", new DeleteCustomerController());

		//mappings.put("user/myPage", new MyPageController());

		/*
		 * mappings.put("/board/messenger/list", new ShowMBoardListController());
		 * mappings.put("/board/messenger/detail", new ShowMBoardController());
		 * mappings.put("/board/messenger/apply", new ApplyMessengerController());
		 * mappings.put("/board/messenger/connect", new ConnectMessengerController());
		 * 
		 */
		
		/*
		 * mappings.put("board/notice/list", new ShowNBoardListController());
		 * mappings.put("board/notice/detail", new ShowNBoardController());
		 * 
		 */
		
		/*
		 * mappings.put("/board/freshmanot/list", new ShowFBoardListController());
		 * mappings.put("/board/freshmanot/detail", new ShowFBoardController());
		 * mappings.put("/board/freshmanot/apply", new ApplyController());
		 * mappings.put("/board/freshmanot/cancel", new CancelController());
		 */
		
		/*
		 * mappings.put("club/recommend", new ShowClubRecommendController());
		 * mappings.put("club/list", new ShowClubListController());
		 * mappings.put("club/detail", new ShowClubDetailController());
		 * mappings.put("club/search", new searchClubController());
		 * 
		 */
		
		/*
		 * mappings.put("/littlemeeting/list", new ShowLMListController());
		 * mappings.put("/littlemeeting/detail", new ShowLMDetailController());
		 * mappings.put("/littlemeeting/apply", new ApplyLMController());
		 * mappings.put("/littlemeeting/cancel", new CalcelLMController());
		 * mappings.put("/littlemeeting/delete", new DeleteLMController());
		 * mappings.put("/littlemeeting/write", new CreateLMController());
		 * 
		 */
		
		mappings.put("/littlemeeting/write/form", new ForwardController("/littlemeeting/write/form.jsp"));

		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String uri) {
		// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
		return mappings.get(uri);
	}
}