package vn.edu.vinaenter.controller.publics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.vinaenter.daos.CatDAO;
import vn.edu.vinaenter.daos.LanDAO;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Land;

@Controller
@RequestMapping("danh-muc/")
public class PublicCategoryController {
	@Autowired
	private LanDAO landDAO;
	@Autowired
	private CatDAO catDAO;
	@ModelAttribute
	public void commonObjects(ModelMap modelMap) {
		List<Category> listCat=catDAO.getItems();
		List<Integer> listCount= new ArrayList<>();
		for (Category cat:listCat) {
			int n=catDAO.countItems(cat);
			listCount.add(n);
		}
		modelMap.addAttribute("listCount", listCount);	
		modelMap.addAttribute("listCat", listCat);	
		List<Land> listMostView=landDAO.getItemsMostView(PageDefine.PUBLIC_ROW_COUNT);
		modelMap.addAttribute("listMostView", listMostView);
		List<Category> listHotCat=catDAO.getItemsHotCat(PageDefine.CAT_ROW_COUNT);
		modelMap.addAttribute("listHot", listHotCat);
	}
	
	@GetMapping({"{cname}-{cid}/page-{page}","{cname}-{cid}"})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page,
						@PathVariable("cid") int cid,
						@PathVariable("cname") String cname) {
		if (page==null) page=1;
		int totalRow=landDAO.countItemsByCat(cid);
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.CAT_ROW_COUNT);
		if (page>sumPage|| page <1) page=1;
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.CAT_ROW_COUNT;
		List<Land> listLand= landDAO.getItemsPaginationByCat(cid,offset);
		modelMap.addAttribute("listLand",listLand);
		return "public.cat";
	}
}
