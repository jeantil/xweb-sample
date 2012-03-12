package fr.xebia.xweb.controller;

import fr.xebia.xweb.model.User;
import fr.xebia.xweb.model.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * User: Jean Helou &lt;jhelou@xebia.fr&gt;
 */
@Controller
public class HomeController {

    @Resource
    ApplicationContext applicationContext;

    @Value("${env}")
    String env;

    @Resource
    UserDao userDao;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("message", "Hello World!");
        mav.addObject("env", env);
        User u = userDao.load(0l);
        mav.addObject("user",u);
        return mav;
    }
}
