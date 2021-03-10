package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.projects.user.utils.RequestUtils;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author zshuo
 * @date 2021/3/3
 **/
@Path("/submit")
public class SubmitController implements PageController {
    private final UserService userService = new UserServiceImpl();

    @Override
    @POST
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = RequestUtils.parseParameter(request, User.class);
            userService.register(user);
            System.out.println("register success.");
            return "success.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("register error. " + e.getMessage());
            return "error.jsp";
        }
    }
}
