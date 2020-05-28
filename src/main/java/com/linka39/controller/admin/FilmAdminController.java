package com.linka39.controller.admin;

import com.linka39.entity.Film;
import com.linka39.entity.WebSiteInfo;
import com.linka39.run.StartupRunner;
import com.linka39.service.FilmService;
import com.linka39.service.WebSiteInfoService;
import com.linka39.util.DateUtil;
import com.linka39.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * 电影控制层
 */
@RestController  //将map键值对自动转换为json数组形式
@RequestMapping("/admin/film")
public class FilmAdminController {

    @Resource
    private FilmService filmService;
    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Value("${imageFilePath}")
    //配置文件注入
    private String imageFilePath;

    @Resource
    private StartupRunner startupRunner;//涉及数据库修改的都需要用到

    /**
     * 添加或修改电影信息
     * @param film
     * @param file
     * @return
     * @throws Exception
     */
    //修改成功返回键值对
    @RequestMapping("/save")
    public Map<String,Object> save(Film film,@RequestParam("imageFile")MultipartFile file)throws Exception{
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename(); //获取文件名
            String suffixName= fileName.substring(fileName.lastIndexOf("."));//获取文件的后缀
            String newFileName = DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+ newFileName));
            film.setImageName(newFileName);
        }
        film.setPublishDate(new Date());
        Map<String,Object> resultMap = new HashMap<>();
        filmService.save(film);
        startupRunner.loadData();
        resultMap.put("success",true);
        return resultMap;
    }

    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum) throws Exception{
        String fileName = file.getOriginalFilename(); //获取文件名
        String suffixName= fileName.substring(fileName.lastIndexOf("."));//获取文件的后缀
        String newFileName = DateUtil.getCurrentDateStr()+suffixName;
        //用common-io来复制文件
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+ newFileName));
        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/filmImage/"+ newFileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }
    /**
     * 分页查询_收录电影网址
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(Film film, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<Film> filmList  = filmService.list(film,page,rows);
        Long total = filmService.getCount(film);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",filmList);
        resultMap.put("total",total);
        return resultMap;
    }

    /**
     * 删除 电影
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam("ids") String ids)throws Exception{
        String idsStr[] = ids.split(",");
        Map<String,Object> resultMap = new HashMap<>();
        boolean flag = true;
        Integer errorId = null;
        for(String each:idsStr){
            Integer filmid = Integer.parseInt(each);
            List<WebSiteInfo>  tempList = webSiteInfoService.getByFilmId(filmid);
            if(tempList.size()>0){
                flag=false;
                errorId = filmid;
                break;
            }else{
                filmService.delete(filmid);
                startupRunner.loadData();
            }
        }
        if(flag){
            resultMap.put("success",true);
        }else{
            resultMap.put("success",false);
            resultMap.put("errorInfo","电影动态信息中存在此电影信息，ID("+errorId+")不可删除");
        }
        return resultMap;
    }

    /**
     * 根据id查找实体
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public Optional<Film> findById(Integer id)throws Exception{
       return filmService.findById(id);
    }

    /**
     * 下拉框模糊查询用到
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<Film> comboList(String q) throws Exception{
        if(StringUtil.isEmpty(q)){
            return null;
        }
        Film film = new Film();
        film.setName(q);
        return filmService.list(film,1,30);//最多查询30条
    }
}
