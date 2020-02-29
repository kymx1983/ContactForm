package com.example.contact_form.controller;

import com.example.contact_form.model.Contact;
import com.example.contact_form.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * IndexController
 */
@Controller
public class IndexController {

    @Autowired
    ContactRepository repository;

    /**
     * お問い合わせの一覧を表示する
     *
     * @return model
     */
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        List<Contact> contents = repository.findAll();
        model.addObject("contents", contents);

        return model;
    }

    /**
     * 更新画面を表示する（登録時）
     * @return model
     */
    @RequestMapping("/new")
    public ModelAndView edit() {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit");
        model.addObject("contact", new Contact());
        return model;
    }


    /**
     * 更新画面を表示する（更新時）
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam long id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("edit");

        Optional<Contact> data = repository.findById(id);

        model.addObject("contact", data.get());
        return model;
    }

    /**
     * お問い合わせの新規作成及び更新を行う
     *
     * @param contact
     * @param result
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView create(@ModelAttribute("contact") @Validated Contact contact, BindingResult result) {

        if (result.hasErrors()) {
            for (FieldError err : result.getFieldErrors()) {
                System.out.println(err.getDefaultMessage());
            }
            ModelAndView model = new ModelAndView();
            model.setViewName("edit");
            model.addObject("contact", contact);
            return model;
        } else {
            if (contact.getId() == 0) {
                // 新規作成の場合、ステータスを初期設定する
                contact.setStatus("未着手");
            }
            contact.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            repository.saveAndFlush(contact);
            return new ModelAndView("redirect:/");
        }
    }

    /**
     * お問い合わせの削除を行う
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam long id) {
        System.out.println("削除");
        ModelAndView model = new ModelAndView();
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }


}
