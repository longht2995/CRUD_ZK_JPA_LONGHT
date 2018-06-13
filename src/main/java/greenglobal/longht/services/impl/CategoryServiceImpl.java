package greenglobal.longht.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import greenglobal.longht.entity.Category;
import greenglobal.longht.services.CategoryService;
@Service("categoryService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return categoryDao.listAll();
	}
	
}
