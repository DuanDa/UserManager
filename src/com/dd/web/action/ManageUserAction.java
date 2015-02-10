package com.dd.web.action;

import com.dd.domain.User;
import com.dd.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ManageUserAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userService = new UserService();
		ArrayList<User> users = new ArrayList<User>();
		for (Object[] object : userService.getUsers()) {
			User user = new User();
			user.setId(Integer.parseInt(object[0].toString()));
			user.setName(object[1].toString());
			users.add(user);
		}
		request.setAttribute("users", users);
		return mapping.findForward("gotoManager");
	}
}
