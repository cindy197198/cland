package vn.edu.vinaenter.controller.publics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
@Repository("cland")
public class PublicLandController {
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
	
	@GetMapping("cat")
	public String cat(ModelMap modelMap) {
		return "public.cat";
	}
	
	@GetMapping("chi-tiet/{lname}-{lid}.html")
	public String detail(ModelMap modelMap,@PathVariable("lid") int lid,@PathVariable("lname") String lname) {
		System.out.println(lname+"  "+lid);
		Land objLand=landDAO.getItem(lid);
		List<Land> relatedLands=landDAO.getRelatedItems(objLand,3);
		modelMap.addAttribute("objLand", objLand);
		modelMap.addAttribute("relatedLands", relatedLands);	
		return "public.detail";
	}
}
