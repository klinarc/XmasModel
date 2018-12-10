package im430.simpleblog.web;

import im430.simpleblog.business.Child;
import im430.simpleblog.dao.ChildDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/cmd/*")
public class SimpleblogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getFormatterLogger(SimpleblogServlet.class);
	private final String VIEWS_DIR = "/WEB-INF/views/";

	ChildDAO childDAO;
	

	public ChildDAO getChildDAO() {
		return childDAO;
	}

	public void setChildDAO(ChildDAO childDAO) {
		this.childDAO = childDAO;
	}

	private void forward(HttpServletRequest req, HttpServletResponse res,
			String page) throws ServletException, IOException {

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				VIEWS_DIR + page);
		rd.forward(req, res);
	}

	@Override
	public void init() throws ServletException {
	    setChildDAO((ChildDAO)(WebApplicationContextUtils
	    					.getWebApplicationContext(getServletContext())
	    					.getBean("childDAO")));
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String module = request.getPathInfo();
		if (null == module) {
			module = "list";
		} else {
			module = module.substring(1);
		}

		if (module.equals("admin")) {
			doAdmin(request, response);
		} else if (module.equals("list")) {
			doList(request, response);
		} else {
			doList(request, response);
		}

	}

	private void doList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("children", childDAO.getAllChildren());
		forward(request, response, "list.jsp");

	}

	private void showAdminMenu(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("children", childDAO.getAllChildren());
		forward(request, response, "adminMenu.jsp");
	}

	private void doAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");

		if (null == cmd) {
			cmd = "menu";
		}

		if (cmd.equals("add")) {
			SimpleToken.set(request);
			forward(request, response, "addBlogEntry.jsp");
		}

		else if (cmd.equals("do-add")) {
			if (SimpleToken.isValid(request)) {
				SimpleToken.remove(request);
				Child logEntry = new Child();
				logEntry.setName(request.getParameter("name"));
				childDAO.addChild(logEntry);
			}

			showAdminMenu(request, response);
		}

		else if (cmd.equals("delete")) {

			int id = -1;
			boolean error = false;
			try {
				id = Integer.parseInt(request.getParameter("entryId"));
				request.setAttribute("child", childDAO.getChild(id));
				SimpleToken.set(request);
			} catch (NumberFormatException e) {
				log.error("Non numeric id");
				error = true;
			} catch (RuntimeException e) {
				log.error("DAO Runtime Exception: " + e);
				error = true;
			} finally {
				if (error) {
					showAdminMenu(request, response);
				} else {
					forward(request, response, "deleteBlogEntry.jsp");
				}
			}
		}

		else if (cmd.equals("do-delete")) {

			int id = -1;
			try {
				if (SimpleToken.isValid(request)) {
					SimpleToken.remove(request);
					id = Integer.parseInt(request.getParameter("entryId"));
					Child child = childDAO.getChild(id);
					if (child != null) {
						childDAO.removeChild(child);
					}
				}
			} catch (NumberFormatException e) {
				log.error("Non numeric id");
			} catch (RuntimeException e) {
				log.error("DAO Runtime Exception: " + e);
			} finally {
				showAdminMenu(request, response);
			}
		}

		else { // includes cmd "menu"

			showAdminMenu(request, response);
		}

	}

}
