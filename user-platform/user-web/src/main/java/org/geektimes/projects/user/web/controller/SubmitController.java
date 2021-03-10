package org.geektimes.projects.user.web.controller;

import org.apache.commons.collections.CollectionUtils;
import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.utils.RequestUtils;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Set;

/**
 * @author zshuo
 * @date 2021/3/3
 **/
@Path("/submit")
public class SubmitController implements PageController {
//
//    @Resource(name = "bean/UserService")
//    private UserService userService;
//
//    @Resource(name = "bean/Validator")
//    private Validator validator;


    @Override
    @POST
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        try {
            User user = RequestUtils.parseParameter(request, User.class);
            Validator validator = ComponentContext.getInstance().getComponent("bean/Validator");
            Set<ConstraintViolation<User>> validate = validator.validate(user);

            if (CollectionUtils.isNotEmpty(validate)) {
                message = validate.stream().map(ConstraintViolation::getMessage).reduce((a, b) -> a + ", " + b).get();
            } else {
                UserService userService = ComponentContext.getInstance().getComponent("bean/UserService");
                boolean result = userService.register(user);

                if (result) {
                    message = user.getName();
                    request.setAttribute("message", message);
                    return "success.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        request.setAttribute("message", message);
        return "register.jsp";
    }
}
