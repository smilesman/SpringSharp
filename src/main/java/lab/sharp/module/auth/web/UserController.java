package lab.sharp.module.auth.web;

import lab.sharp.core.service.BaseService;
import lab.sharp.core.web.BaseController;
import lab.sharp.module.auth.entity.User;
import lab.sharp.module.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends BaseController<User, Long> {

    @Autowired
    private UserService userService;


    @Override
    protected BaseService<User, Long> getEntityService() {
        return userService;
    }


}
