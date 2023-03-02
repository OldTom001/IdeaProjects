package com.jeff.jeffhome.controller.admin;

import com.jeff.jeffhome.entity.BlogLink;
import com.jeff.jeffhome.service.LinkService;

import com.jeff.jeffhome.util.PageQueryUtil;
import com.jeff.jeffhome.util.Result;
import com.jeff.jeffhome.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/admin")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping("/links")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "links");
        return "admin/link";
    }

    @GetMapping("/links/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(linkService.getBlogLinkPage(pageUtil));
    }

    /**
     * 友链添加
     */
    @RequestMapping(value = "/links/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription) {
        if (linkType == null || linkType < 0 || linkRank == null || linkRank < 0 || !StringUtils.hasText(linkName) || !StringUtils.hasText(linkName) || !StringUtils.hasText(linkUrl) || !StringUtils.hasText(linkDescription)) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        BlogLink link = new BlogLink();
        link.setLinkType(linkType.byteValue()); //相当于将integer转换为byte
        link.setLinkRank(linkRank);
        link.setLinkName(linkName);
        link.setLinkUrl(linkUrl);
        link.setLinkDescription(linkDescription);
        return ResultGenerator.genSuccessResult(linkService.saveLink(link));
    }

    /**
     * 详情
     */
    @GetMapping("/links/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        BlogLink link = linkService.selectById(id);
        return ResultGenerator.genSuccessResult(link);
    }

    /**
     * 友链修改
     */
    @RequestMapping(value = "/links/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("linkId") Integer linkId,
                       @RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription) {
        BlogLink tempLink = linkService.selectById(linkId);
        if (tempLink == null) {
            return ResultGenerator.genFailResult("无数据！");
        }
        if (linkType == null || linkType < 0 || linkRank == null || linkRank < 0 || !StringUtils.hasText(linkName) || !StringUtils.hasText(linkName) || !StringUtils.hasText(linkUrl) || !StringUtils.hasText(linkDescription)) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        tempLink.setLinkType(linkType.byteValue());
        tempLink.setLinkRank(linkRank);
        tempLink.setLinkName(linkName);
        tempLink.setLinkUrl(linkUrl);
        tempLink.setLinkDescription(linkDescription);
        return ResultGenerator.genSuccessResult(linkService.updateLink(tempLink));
    }

    /**
     * 友链删除
     */
    @RequestMapping(value = "/links/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (linkService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

}