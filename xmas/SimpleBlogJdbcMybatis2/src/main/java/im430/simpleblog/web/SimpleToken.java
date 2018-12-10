package im430.simpleblog.web;

import javax.servlet.http.*;

/**
 * Manage Token To Ensure Form Workflow.
 * 
 * <p>
 * IM430 Hypermedia Frameworks.<br>
 * <a href="http://www.fh-ooe.at/im">Interactive Media</a>.
 * </p>
 * 
 * @author Rimbert Rudisch-Sommer
 */
public class SimpleToken {

	public static void set(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		long systime = System.currentTimeMillis();
		String token = new Long(systime).toString();
		token += session.getId();
		req.setAttribute("token", token);
		session.setAttribute("token", token);
	}

	public static boolean isValid(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		String requestToken = req.getParameter("token");
		String sessionToken = (String) session.getAttribute("token");
		if (requestToken == null || sessionToken == null)
			return false;
		else {
			requestToken = requestToken.trim();
			sessionToken = sessionToken.trim();
			return requestToken.equals(sessionToken);
		}
	}

	public static void remove(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		session.removeAttribute("token");
	}

}
