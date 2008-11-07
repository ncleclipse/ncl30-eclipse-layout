package br.ufma.deinf.laws.ncleclipse.layout.model;

public class Region extends Node {
	// Id declared in Node
	private String title;
	private String bottom;
	private String zIndex;

	public Region(){
		super();
		title = "Unknown";
		bottom = zIndex = null;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLeft() {
		return layout.x;
	}

	public void setLeft(String left) {
		this.layout.x = new Integer(left);
	}
	
	public void setLeft(int left) {
		this.layout.x = left;
	}

	public int getTop() {
		return layout.y;
	}

	public void setTop(String top) {
		if(top.endsWith("px"))
			this.layout.y = new Integer(top.substring(0, top.length()-2));
		else this.layout.y = new Integer(top);
	}
	
	public void setTop(int top) {
		this.layout.y = top;
	}
	
	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public int getHeight() {
		return layout.height;
	}

	public void setHeight(String height) {
		if(height.endsWith("px"))
			this.layout.height = new Integer(height.substring(0, height.length()-2));
		else this.layout.height = new Integer(height);
	}
	public void setHeigth(int height){
		this.layout.height = height;
	}

	public int getWidth() {
		return this.layout.width;
	}

	public void setWidth(String width) {
		if(width.endsWith("px"))
			this.layout.width = new Integer(width.substring(0, width.length()-2));
		else this.layout.width = new Integer(width);
	}
	
	public void setWidth(int width){
		this.layout.width = width;
	}

	public String getZIndex() {
		return zIndex;
	}

	public void setZIndex(String index) {
		zIndex = index;
	}

}
