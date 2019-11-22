package controller.board_freshmanot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.customer.CustomerSessionUtils;
import model.ApplicationBoard;
import model.service.FreshmanotBoardManager;

public class ShowFBoardController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplicationBoard board = null;
		FreshmanotBoardManager manager = FreshmanotBoardManager.getInstance();
		
		int departNo = Integer.parseInt(request.getParameter("depart_no"));
		int boardNo = Integer.parseInt(request.getParameter("board_no"));
		
		request.setAttribute("curUserId", 
				CustomerSessionUtils.getLoginCustomerId(request.getSession()));		
		
		try {
			board = manager.showDetail(departNo, boardNo);

		} catch (Exception e) {
			return "redirect:/view/board/list";
		}
		
		request.setAttribute("board", board);
		
		if (request.getParameter("updateFailed") != null) {
			// ���� �õ� ����
	    	request.setAttribute("exception", 
				new IllegalStateException("������ ���� ���� ���� ������ �� �����ϴ�."));            
			request.setAttribute("updateFailed", true);
		}
		else if (request.getParameter("deleteFailed") != null) {
			// ���� �õ� ����	
	    	request.setAttribute("exception", new IllegalStateException("�ٸ� ������� ���� ������ �� �����ϴ�."));
			request.setAttribute("deleteFailed", true);
		}
		
		else if (request.getParameter("answerFailed") != null) {
			request.setAttribute("exception", new IllegalStateException("�����ڸ� �亯�� �� �ֽ��ϴ�."));
			request.setAttribute("answerFailed", true);
		}
		
		return "/view/board/freshmanot/detail.jsp";
	}

}

