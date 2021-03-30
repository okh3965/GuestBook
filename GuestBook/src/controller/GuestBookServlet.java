package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestBookDao;
import dao.GuestBookDaoImpl;
import vo.GuestBookVo;


@WebServlet(name="GuestBook", urlPatterns="/gb")
public class GuestBookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if("delete".equals(action)) {
			String password = req.getParameter("password");
			System.out.println(password);
			GuestBookDao dao = new GuestBookDaoImpl();
			dao.delete(password);
			resp.sendRedirect(req.getContextPath() + "/gb");
		} else if("deleteform".equals(action))	{
			RequestDispatcher rd =
					getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(req, resp);			
		} else {
			GuestBookDao dao = new GuestBookDaoImpl();
			List<GuestBookVo> list = dao.getList();
			req.setAttribute("list", list);
			
			RequestDispatcher rd =
					getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
		
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String content = req.getParameter("content");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			GuestBookDao dao = new GuestBookDaoImpl();
			
			dao.insert(vo);
			resp.sendRedirect(req.getContextPath() + "/gb");
		} else {
			doGet(req, resp);
		}		
	}
}
