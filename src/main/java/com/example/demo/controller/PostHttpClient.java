package com.example.demo.controller;


import com.example.demo.utils.EcomResultDO;
import com.example.demo.utils.FastJsonUtil;
import com.example.demo.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("tr/")
public class PostHttpClient {

    final String  token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJqdGkiOiIxNTM3NDMwNTEwMDU2N2U3NmI4ODcxYjQ0NDZhZDgxNjQxYzNiNWZmODkzMjgiLCJzdWIiOiJ7XCJhZG1pbkZsYWdcIjowLFwiY29tcGFueU5hbWVcIjpcIuazouWPuOeZu-a1i-ivlV_pu4RcIixcImNvbXBhbnlOb1wiOjY4LFwiZGVmaW5lUm9sZXNcIjpcIjBcIixcImRpc3RyaWJ1dG9yRnJlZXplRmxhZ1wiOlwiTlwiLFwiZGlzdHJpYnV0b3JOYW1lXCI6XCLms6Llj7jnmbvmtYvor5Vf6buEXCIsXCJkaXN0cmlidXRvck5vXCI6NjAsXCJkaXN0cmlidXRvclR5cGVcIjowLFwiZW1haWxOb1wiOlwiemhhbmcuZ3VvZmFuZ0Bib3NpZGVuZy5jb21cIixcIm5pY2tOYW1lXCI6XCLlvKDlm73oirNfMDAxNTc1XCIsXCJzdG9yZUxpc3RcIjpcIjI5LDIwNFwiLFwic3lzdGVtUm9sZXNcIjpcIjM5XCIsXCJ1c2VySWRcIjozMDUwOTQ0MzQ1MjUwNzQ1NTgxLFwidXNlck5hbWVcIjpcIuW8oOWbveiKs1wiLFwidXNlck5vXCI6XCIwMDE1NzVcIixcInVzZXJQaWN0dXJlXCI6XCJcIixcIndhcmVob3VzZU5hbWVcIjpcIlwiLFwid2FyZWhvdXNlTm9cIjoyLFwid2FyZWhvdXNlVHlwZVwiOjJ9IiwiaXNzIjoiMDowOjA6MDowOjA6MDoxIiwiaWF0IjoxNTM3NDMwNTEwLCJleHAiOjE1Mzc5MzA1MTB9.cEvgzGiJypkfK88NTpKBInqL98m94p0COHvP9O2GKC4";
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public EcomResultDO<Object> getLIst(){
        String str = "";
        EcomResultDO<Object> jsonStr = HttpUtil.httpPost(str,"/om/omFcShop/toShopPage",token);
        System.out.println(jsonStr);
        Map<String,Object> dto = FastJsonUtil.toObject(jsonStr.getData().toString(),Map.class);
        System.out.println(dto);
        return jsonStr;
    }

    @RequestMapping("/index")
    public  String index(){
        return "index.jsp";
    }
}
