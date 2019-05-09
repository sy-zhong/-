package test1.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import test1.test1.entity.EEntity;
import test1.test1.service.CService;
import test1.test1.service.SCService;

import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

@RestController
public class TController {

    @Autowired
    CService cService;
    @Autowired
    SCService scService;

    @RequestMapping("/tea/Viewscore")
    public ModelAndView viewscore(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("T/score");
        return mv;
    }

    @RequestMapping("/tea/view_course")
    public Page<Map<String,Object>> tea_view_course(HttpSession httpSession,Integer page,Integer size){
//        System.out.println(httpSession.getAttribute("userId"));
//        System.out.println(page);
//        System.out.println(size);
        return cService.findbygh((Integer)(httpSession.getAttribute("userId")),page,size);
    }

    @RequestMapping("/tea/view_sc")
    public Page<Map<String,Object>> tea_view_sc(HttpSession httpSession,Integer page,Integer size,Integer kh){
//        System.out.println(httpSession.getAttribute("userId"));
//        System.out.println(page);
//        System.out.println(size);
        return scService.showsc_t_c((Integer)(httpSession.getAttribute("userId")),kh,page-1,size);
    }

//    private Integer xh;
//    private String xq;
//    private Integer kh;
//    private Integer gh;
//    private Integer pscj;
//    private Integer kscj;
//    private Integer zpcj;
//    private String sksj;
    @RequestMapping("/change_cj")
    public boolean change_cj(HttpSession httpSession, @RequestBody Map<String,Object>[] ids){
        //eEntity.setXq("2012-2013冬季");
        //if( scService.saveSC(eEntity)) return true;
        for( Map<String,Object> v:ids){
            Integer xh = Integer.parseInt(v.get("xh").toString());
            Integer kh = Integer.parseInt(v.get("kh").toString());
            Integer gh = Integer.parseInt(httpSession.getAttribute("userId").toString());
            Integer pscj=null,kscj=null,zpcj=null;
            if(v.get("pscj")!=null) pscj = Integer.parseInt(v.get("pscj").toString());
            if(v.get("kscj")!=null) kscj = Integer.parseInt(v.get("kcsj").toString());
            if(v.get("zpcj")!=null) zpcj = Integer.parseInt(v.get("zpcj").toString());
            scService.updatecj(xh,kh,gh,pscj,kscj,zpcj);
//            if(!scService.saveSC((EEntity)(v)))  return false;
//            System.out.println(v.get("xh"));
        }
        return true;
    }
}
