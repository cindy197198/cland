package vn.edu.vinaenter.controller.publics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.edu.vinaenter.daos.CatDAO;
import vn.edu.vinaenter.daos.LanDAO;
import vn.edu.vinaenter.defines.PageDefine;
import vn.edu.vinaenter.models.Category;
import vn.edu.vinaenter.models.Land;

@Controller
public class PublicIndexController {
	@Autowired
	private CatDAO catDAO;
	@Autowired
	private LanDAO landDAO;
	@GetMapping({"{page}",""})
	public String index(ModelMap modelMap,@PathVariable(value="page",required=false) Integer page) {
		if (page==null) page=1;
		int totalRow=landDAO.countItems();
		int sumPage=(int) Math.ceil((float)totalRow/PageDefine.PUBLIC_ROW_COUNT);
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("page",page);		
		int offset= (page-1)* PageDefine.PUBLIC_ROW_COUNT;
		List<Land> listLand= landDAO.getItemsPagination(offset);
		modelMap.addAttribute("listLand",listLand);
		List<Land> listMostView=landDAO.getItemsMostView(3);
		modelMap.addAttribute("listMostViews", listMostView);
		return "public.index";
	}
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
}
