package greenglobal.longht.services;

import java.util.List;

import greenglobal.longht.entity.Post;
import greenglobal.longht.entity.PostSearch;

public interface PostService {
	public List<Post> listAll();
	public void addPost(Post post);
	public void deletePost(Post post);
	public List<Post> search(PostSearch post);
}
