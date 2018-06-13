package greenglobal.longht;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.impl.BinderUtil;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;

import greenglobal.longht.entity.Category;
import greenglobal.longht.entity.Log;
import greenglobal.longht.entity.Post;
import greenglobal.longht.entity.PostSearch;
import greenglobal.longht.services.CategoryService;
import greenglobal.longht.services.MyService;
import greenglobal.longht.services.PostService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {
	@WireVariable
	private CategoryService categoryService;
	@WireVariable
	private PostService postService;
	@WireVariable
	private MyService myService;
	private ListModelList<Log> logListModel;
	private String message;
	private ListModelList<Category> categoryListModel;
	private ListModelList<Post> postListModel;
	private Post post;
	private PostSearch postSearch;
	@Init
	public void init() {
		List<Category> categoryList = categoryService.listAll();
		categoryListModel = new ListModelList<Category>(categoryList);
		List<Post> postList = postService.listAll();
		postListModel = new ListModelList<Post>(postList);
		List<Log> logList = myService.getLogs();
		logListModel = new ListModelList<Log>(logList);
		post = new Post();
		postSearch = new PostSearch();
	}
	@Wire
	private Popup popup;
	@Command
	@NotifyChange("post")
	public void addPost() {
		postService.addPost(post);
		BindUtils.postGlobalCommand(null, null, "refresh", null);
		post = new Post();
		Clients.showNotification("Add Success","info", null, "top_center", 4100);
	}
	@Command
	@NotifyChange("post")
	
	public void editPost(@BindingParam(value = "post")Post p) {
		post = p;
	}
	@Command
	public void addPostP(@ContextParam (ContextType.VIEW) Component view) {
		
		
	}
	@Command
	@NotifyChange("postListModel")
	public void searchPost() {
		List<Post> postList = postService.search(postSearch);
		postListModel = new ListModelList<Post>(postList);
		
	}
	@Command
	@NotifyChange("postListModel")
	public void deletePost(@BindingParam(value = "post") final Post post) {
		Messagebox.show("Something is changed. Are you sure?", 
			    "Question", Messagebox.OK | Messagebox.CANCEL,
			    Messagebox.QUESTION,
			        new org.zkoss.zk.ui.event.EventListener(){
						public void onEvent(Event event) throws Exception {
							if(Messagebox.ON_OK.equals(event.getName())){
								postService.deletePost(post);
								BindUtils.postGlobalCommand(null, null, "refresh", null);
								Clients.showNotification("Remove Success","info", null, "top_center", 4100);
			                }else if(Messagebox.ON_CANCEL.equals(event.getName())){
			                    //Cancel is clicked
			                }
							
						}
			        }
			    );
		
	}
	@GlobalCommand
	@NotifyChange("*")
	public void refresh() {
		List<Post> postList = postService.listAll();
		postListModel = new ListModelList<Post>(postList);
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public ListModelList<Post> getPostListModel() {
		return postListModel;
	}


	public void setPostListModel(ListModelList<Post> postListModel) {
		this.postListModel = postListModel;
	}

	
	public PostSearch getPostSearch() {
		return postSearch;
	}
	public void setPostSearch(PostSearch postSearch) {
		this.postSearch = postSearch;
	}
	public ListModelList<Category> getCategoryListModel() {
		return categoryListModel;
	}


	public void setCategoryListModel(ListModelList<Category> categoryListModel) {
		this.categoryListModel = categoryListModel;
	}


	@Command
	public void addLog() {
		if(Strings.isBlank(message)) {
			return;
		}
		Log log = new Log(message);
		log = myService.addLog(log);
		logListModel.add(log);
	}

	@Command
	public void deleteLog(@BindingParam("log") Log log) {
		myService.deleteLog(log);
		logListModel.remove(log);
	}

}
