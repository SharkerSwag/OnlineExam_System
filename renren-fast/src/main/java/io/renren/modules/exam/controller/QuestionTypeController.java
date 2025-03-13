package io.renren.modules.exam.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.exam.entity.QuestionTypeEntity;
import io.renren.modules.exam.service.QuestionTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 题目类型
 *

 */
@RestController
@RequestMapping("/exam/questiontype")
public class QuestionTypeController {
    @Autowired
    private QuestionTypeService questionTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionTypeService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/all")
    public R all() {
        return R.ok().put("data", questionTypeService.list(new QueryWrapper<QuestionTypeEntity>().eq("is_deleted", 0)));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("exam:questiontype:info")
    public R info(@PathVariable("id") Integer id){
		QuestionTypeEntity questionType = questionTypeService.getById(id);

        return R.ok().put("questionType", questionType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:questiontype:save")
    public R save(@RequestBody QuestionTypeEntity questionType){
		questionTypeService.save(questionType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:questiontype:update")
    public R update(@RequestBody QuestionTypeEntity questionType){
		questionTypeService.updateById(questionType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:questiontype:delete")
    public R delete(@RequestBody Integer[] ids){
		questionTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
