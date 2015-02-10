package com.dd.web.action;

import com.dd.utils.SqlHelper;
import com.dd.web.form.UserForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LoginAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SqlHelper sqlHelper = new SqlHelper();
		UserForm userForm = (UserForm) form;
		String loginUsername = userForm.getUsername();
		ArrayList<Object[]> arrayList = sqlHelper.executeQuery("select * from students where name=?", new String[]{loginUsername});
		if (arrayList.size() == 1) {
			request.setAttribute("username", loginUsername);
			return mapping.findForward("login_ok");
		}

		return mapping.findForward("login_error");
	}
}
