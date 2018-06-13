package greenglobal.longht.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import greenglobal.longht.entity.Post;
import greenglobal.longht.entity.PostSearch;
import greenglobal.longht.services.PostService;
@Service("postService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDao postDao;
	public List<Post> listAll() {
		// TODO Auto-generated method stub
		return postDao.listAll();
	}
	public void addPost(Post post) {
		// TODO Auto-generated method stub
		postDao.addPost(post);
	}
	public void deletePost(Post post) {
		// TODO Auto-generated method stub
		postDao.delete(post);
	}
	public List<Post> search(PostSearch post) {
		return postDao.findBy(post);
	}
	
}
