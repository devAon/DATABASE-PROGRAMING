package controller.club;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Club;
import model.Customer;
import model.service.ClubManager;

public class ShowClubRecommendController  implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int customer_no = Integer.parseInt(request.getParameter("customer_no"));
		
		ClubManager manager = ClubManager.getInstance();
		List<Club> clubList = manager.showRecommend(customer_no);

		// commList ��ü�� request�� �����Ͽ� Ŀ�´�Ƽ ����Ʈ ȭ������ �̵�(forwarding)
		request.setAttribute("clubList", clubList);				
		return "/community/recommend.jsp";   
	}
}
