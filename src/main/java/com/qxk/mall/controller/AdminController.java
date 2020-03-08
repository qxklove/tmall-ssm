package com.qxk.mall.controller;

import com.qxk.mall.pojo.Seller;
import com.qxk.mall.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * @Author: laijianzhen
 * @Date: 2019/5/4 15:40
 */

@Controller
@RequestMapping("")
public class AdminController {

    @Autowired
    SellerService sellerService;

    @RequestMapping("adminregister")
    public String register(Model model, Seller seller) {
        String name =  seller.getName();
        name = HtmlUtils.htmlEscape(name);
        seller.setName(name);
        boolean exist = sellerService.isExist(name);

        if(exist){
            String m ="用户名已经被使用,不能使用";
            model.addAttribute("msg", m);


            return "admin/register";
        }
        sellerService.add(seller);

        return "redirect:registerSuccessPage";
    }

    @RequestMapping("adminlogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        Seller seller = sellerService.get(name,password);

        if(null==seller){
            model.addAttribute("msg", "账号密码错误");
            return "admin/login";
        }
        session.setAttribute("seller", seller);
        return "redirect:admin_category_list";
    }

    @RequestMapping("adminlogout")
    public String logout( HttpSession session) {
        session.removeAttribute("seller");
        return "admin/login";
    }

}
