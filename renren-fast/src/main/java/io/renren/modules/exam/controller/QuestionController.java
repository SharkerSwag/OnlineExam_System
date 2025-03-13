package io.renren.modules.exam.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.exam.entity.QuestionEntity;
import io.renren.modules.exam.service.QuestionService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 题目
 *

 */
@RestController
@RequestMapping("/exam/question")
public class QuestionController extends AbstractController{
    @Autowired
    private QuestionService questionService;

    /**
     * 题目列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 题目信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		QuestionEntity question = questionService.getById(id);
        return R.ok().put("question", question);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:question:save")
    public R save(@RequestBody QuestionEntity question){
        question.setCreateBy(getUser().getWorkNo());
		questionService.save(question);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:question:update")
    public R update(@RequestBody QuestionEntity question){
        question.setUpdateBy(getUser().getWorkNo());
		questionService.updateById(question);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:question:delete")
    public R delete(@RequestBody Integer[] ids){
		questionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
