package top.niandui.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * 文件上传
     * @return
     */
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传... ");

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
        ServletFileUpload upload = new ServletFileUpload(factory);
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
