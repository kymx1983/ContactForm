package com.example.contact_form.controller;

import com.example.contact_form.model.Contact;
import com.example.contact_form.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    ContactRepository repository;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        List<Contact> contents = repository.findAll();
        model.addObject("contents", contents);

        return model;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView create(@ModelAttribute("contact") @Validated Contact contact, BindingResult result){

        if(result.hasErrors()) {
            System.out.println("エラーが出ていますよ！！");
            ModelAndView model = new ModelAndView();
            model.setViewName("edit");
            model.addObject("contact", contact);
            return model;
        } else {
            System.out.println("正常ですよ");
            System.out.println("id:" + contact.getId());
            contact.setStatus("未着手");
            contact.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            repository.saveAndFlush(contact);
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping("/new")
    public ModelAndView newContact(){
        ModelAndView model = new ModelAndView();
        model.setViewName("edit");
        model.addObject("contact", new Contact());
        return model;
    }



    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam long id){
        ModelAndView model = new ModelAndView();
        model.setViewName("edit");

        Optional<Contact> data = repository.findById(id);

        model.addObject("contact", data.get());
        return model;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam long id){
        System.out.println("削除");
        ModelAndView model = new ModelAndView();
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }


}
