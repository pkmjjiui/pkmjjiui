package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "param1")
public class MyController {


/*    @RequestMapping("/hello.do")
    public String hello(){
      System.out.println("---hello()---");
      return "hello";
    }
*/

/*
@RequestMapping("/hello")
public ModelAndView hello(){
    System.out.println("---hello()---");//进入方法的标志
    ModelAndView mav = new ModelAndView();
    mav.setViewName("hello");
    //添加到ModelAndView中的对象，存入了request作用域
    mav.addObject("msg","I‘m Peter!");
   return mav;
}*/

    /*
        @RequestMapping("/hello")
        public String hello(String username, Model model){
            System.out.println("---hello()---");//进入方法的标志
            //将传入的参数添加到Model对象(存入了request作用域)
           model.addAttribute("username",username);
           model.addAttribute(username);
            return "hello";
        }
    */

    @RequestMapping(value = "/load.do/{filename}")
    public void load(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" +doFilename2(filename,request));
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("下载路径=" + realPath);
        FileInputStream is = new FileInputStream(new File(realPath, filename));
        OutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        os.close();
        is.close();
    }

    private String doFilename2(String filename, HttpServletRequest request) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toUpperCase().indexOf("FIREFOX")>0) {
            filename = "?UTF-8?B?"+new String();//TBD
    }else {
           filename = URLEncoder.encode(filename,"utf-8");
        }
        return filename;
}
/*
    @RequestMapping("/hello")
    public String hello(String username, Map<String,String> map){
        System.out.println("---hello()---");//进入方法的标志
        map.put("username",username);
        return "hello";
    }


    @RequestMapping(value = "/param1",method = {RequestMethod.GET})
    public String param1(HttpServletRequest request){
        //接收client发来的数据
        String name = request.getParameter("name");
        System.out.println("name="+name);//打印接收过来的数据(参数形式)
        request.setAttribute("name",name);//将数据存入request
        //调用业务层
        //页面跳转
        return "hello";
    }

    @RequestMapping(value = "/param2",method = {RequestMethod.GET,RequestMethod.POST})
    public String param2(HttpServletRequest request, HttpSession session){
        //接收client发来的数据
        String name = request.getParameter("username");//获取数据
        String age = request.getParameter("age");//获取数据
        System.out.println("name="+name+",age="+age);//打印数据
        session.setAttribute("age",age);//将数据存入session
        request.setAttribute("name",name);//将数据存入request

        return "hello";//跳转至jsp/hello.jsp页面
    }

    @RequestMapping(value = "param3",method = {RequestMethod.POST})
    public String param3(String username,int age){
        System.out.println("----param3()----");
        System.out.println("username="+username);
        System.out.println("age="+age);
        return "hello";
    }

    @RequestMapping(value = "param4",method = {RequestMethod.POST})
    public String param4(
     @RequestParam(value = "username",required = false) String u,
     @RequestParam(value = "age",defaultValue = "18") int a,HttpSession session){
        System.out.println("----param4()----");
        System.out.println("u="+u);
        System.out.println("a="+a);
        session.setAttribute("name",u);
        return "redirect:test";//url:/WEB-INF/jsp/hello.jsp(默认为转发跳转)
    }

    @RequestMapping(value = "param5",method = {RequestMethod.POST})
    public String param5(User user,HttpSession session){
        System.out.println("----param5()----");
        System.out.println("username="+user.getUsername());
        System.out.println("age="+user.getAge());
        session.setAttribute("name",user.getUsername());
        return "redirect:test";
    }

    @RequestMapping("test")//test.do
    public String test(){
        System.out.println("----test()----");
        return "hello";//hello.jsp
    }
    @RequestMapping("/reg")
    public String reg(User user){
        System.out.println("username="+user.getUsername());
        System.out.println("pwd="+user.getAge());
        System.out.println("birthday="+user.getBirthday());
        System.out.println("city="+user.getAddress().getCity());
        System.out.println("street="+user.getAddress().getStreet());
        System.out.println("phone="+user.getAddress().getPhone());
        return "/param1/test";
    }

 */
    public String doFileName(String filename) {
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//获取uuid字符，避免名称重复
        System.out.println("上传文件名=" + uuid);
        return uuid + "." + extension;
    }

    @RequestMapping(value = "upload2", method = {RequestMethod.POST})
    public String fileUpload2(MultipartFile[] images, HttpServletRequest request) throws IOException {
        for (MultipartFile image : images) {
            InputStream is = image.getInputStream();//输入流
            String filename = image.getOriginalFilename();//文件名称
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径=" + realPath);
            FileOutputStream os = new FileOutputStream(new File(realPath, doFileName(filename)));
            int size = IOUtils.copy(is, os);
            System.out.println("完成上传size=" + size + "Byte");
            os.close();
            is.close();//关闭流
        }
        return "welcome";
    }
}
