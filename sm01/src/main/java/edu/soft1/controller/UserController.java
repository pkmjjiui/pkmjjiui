package edu.soft1.controller;

import edu.soft1.pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    public String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//获取uuid字符，避免名称重复
        System.out.println("上传文件名="+uuid);
        return uuid+"."+extension;
    }


    @RequestMapping("/hello")
    public String hello(String username){
      System.out.println("---hello()---");
      return "hello";
    }


    @RequestMapping(value = "/login")
    public String login(User user, HttpSession session,HttpServletRequest request){
        System.out.println("----login()----");//进入控制值
        // 获取username的值
        // request.getParameter();
        int flag = 1;//调用业务层(业务层调用dao层)，获取flag的值
        if(flag == 1){
            System.out.println("username="+user.getUsername());
         //   session.setMaxInactiveInterval(10);//10秒钟有效期
            session.setAttribute("user",user);//登录对象(名值对的方式)放入session
            return "welcome";//登录成功
        }
        System.out.println("登陆失败，返回首页index");
        request.setAttribute("error","用户名或密码不正确");
        return "forward:/index.jsp";//
    }

    @RequestMapping("/delete")//被拦截器拦截的方法
    public String delete(HttpServletRequest request){
        System.out.println("----执行delete()成功！----");
        request.setAttribute("CRUDmsg","删除功能执行完毕！");
        return "hello";
    }

    @RequestMapping("/welcome")//被拦截器拦截的方法
    public String welcome(){
        System.out.println("----welcome()----");
        return "welcome";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //清空session
        session.invalidate();
        System.out.println("已登出");
        return "redirect:/index.jsp";//重定向跳转至首页
    }


    @RequestMapping(value = "upload.do",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image, HttpServletRequest request, Map<String,Object> map) throws IOException {
        InputStream is = image.getInputStream();//输入流
        String filename = image.getOriginalFilename();//文件名称
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        os.close();is.close();//关闭流
        if (size > 0) {
            map.put("msg","uploadSuccess");
            System.out.println("上传文件成功,size="+size+"Byte");
        }
        return "welcome";
    }

    @RequestMapping(value = "upload2.do",method = {RequestMethod.POST})
    public String fileUpload2(MultipartFile[] images, HttpServletRequest request, Map<String,Object> map) throws IOException {
        InputStream is = null;OutputStream os = null;
        int count = 0;
        for (MultipartFile image :images) {
            is = image.getInputStream(); //获取文件的输入流文对象
            String filename = image.getOriginalFilename();//获取文件的真实姓名
       //     System.out.println("文件原名称=" + filename);
            if (filename.equals("")) {
         //       System.out.println("空字符串,进入下一轮循环");
                continue;//进入下一轮循环
            }
            String realPath = request.getServletContext().getRealPath("/images");
            System.out.println("上传路径realPath=" + realPath);
            //根据images文件夹的真实路径和文件的名字创建输出流对象
            os = new FileOutputStream(new File(realPath, doFileName(filename)));
            int size = IOUtils.copy(is, os);//把输入流数据写入输出流,完成文件的上传
            if(size > 0){count++;}
        }
        os.close();is.close();//释放资源,原则:先开后关，先关后开
        map.put("msg2",count);
        System.out.println("上传成功"+count+"张图片！");
        return "welcome";
    }
/*
    @RequestMapping("/reg")
    public String reg(User user) {
        System.out.println("username=" + user.getUsername());
        System.out.println("pwd=" + user.getAge());
        System.out.println("birthday=" + user.getBirthday());
        System.out.println("city=" + user.getAddress().getCity());
        System.out.println("street=" + user.getAddress().getStreet());
        System.out.println("phone=" + user.getAddress().getPhone());
        return "hello";
    }

 */
}
