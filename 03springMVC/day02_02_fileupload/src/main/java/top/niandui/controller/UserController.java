package top.niandui.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 跨服务器方式，文件上传
     * @param upload    ：名称必须和表单的文件<input>的name属性相同
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload3")
    public String fileUpload3(MultipartFile upload) throws Exception {
        System.out.println("文件上传... 3 跨服务器方式");

        // 定义上传文件服务器路径: 文件服务器路径 + 文件路径
        String path = "http://localhost:9090/day02_02_fileuploadserver_war_exploded/uploads/";


        // 说明上传文件项
        // 获取到上传文件的名称
        String fileName = upload.getOriginalFilename(); // 获取文件传统的名称，不带路径
        // 把文件名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;
        // 完成文件上传，跨服务器上传

        // 创建客户端对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + fileName);

        // 上传文件，通过字节的方式
        webResource.put(upload.getBytes());

        return "success";
    }

    /**
     * SpringMVC方式，文件上传
     * @param request
     * @param upload    ：名称必须和表单的文件<input>的name属性相同
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("文件上传... 2 SpringMVC方式");

        // 文件上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断，该路径是否存在
        File file =  new File(path);
        if (!file.exists()) {
            // 创建该文件夹
            file.mkdirs();
        }

        // 说明上传文件项
        // 获取到上传文件的名称
        String fileName = upload.getOriginalFilename(); // 获取文件传统的名称，不带路径
        // 把文件名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;
        // 完成文件上传
        upload.transferTo(new File(path, fileName));
        System.out.println(new File(path, fileName).getAbsolutePath());

        return "success";
    }

    /**
     * 传统方式，文件上传
     * @return
     */
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传... 1 传统方式");

        // 使用fileupload组件完成文件上传
        // 文件上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断，该路径是否存在
        File file =  new File(path);
        if (!file.exists()) {
            // 创建该文件夹
            file.mkdirs();
        }

        // 解析request对象，获取文件上传项
        DiskFileItemFactory factory = new DiskFileItemFactory(); // 磁盘文件项工厂
        ServletFileUpload upload = new ServletFileUpload(factory); // 上传文件处理
        // 解析request
        List<FileItem> items = upload.parseRequest(request);
        // 遍历
        for (FileItem item : items) {
            // 进行判断，当前的item对象是否是上传文件项
            if (item.isFormField()) {
                // 说明普通表单项
            } else {
                // 说明上传文件项
                // 获取到上传文件的名称
                String fileName = item.getName();
                // 把文件名称设置唯一值，uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "_" + fileName;
                // 完成文件上传
                item.write(new File(path, fileName));
                // 删除临时文件
                item.delete();
                System.out.println(new File(path, fileName).getAbsolutePath());
            }
        }
        return "success";
    }
}
