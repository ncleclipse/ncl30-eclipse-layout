package br.ufma.deinf.laws.ncleclipse.layout.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Node{
	protected String id;
	protected Rectangle layout;

	//to work with %
	protected String width_perc;
	protected String height_perc;
	protected String x_perc;
	protected String y_perc;
	
	protected List <Node> children;
	protected Node parent;
	protected PropertyChangeSupport listeners;
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";

	
	public Node(){
		this.id = "Unknown";
		this.layout = new Rectangle(10,10,100,100);
		this.children = new ArrayList<Node>();
		this.parent = null;
		this.listeners = new PropertyChangeSupport(this);
	}
	
	public Node(Rectangle layout){
		this.id = "Unknown";
		this.layout = layout;
		this.children = new ArrayList<Node>();
		this.parent = null;
		this.listeners = new PropertyChangeSupport(this);
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setLayout(Rectangle newLayout){
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		getListeners().firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}
	
	public PropertyChangeSupport getListeners() {
		return listeners;
	}

	public void setListeners(PropertyChangeSupport listeners) {
		this.listeners = listeners;
	}

	public Rectangle getLayout(){
		return this.layout;
	}
	
	public boolean addChild(Node child){
		boolean b = this.children.add(child);
		if(b){
			child.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, child);
		}
		return b;
	}
	
	public boolean removeChild(Node child){
		boolean b = this.children.remove(child);
		if(b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}
	
	public List <Node> getChildrenArray(){
		return this.children;
	}
	
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	/**
	 * Listening user events and change the model
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	     listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	     listeners.removePropertyChangeListener(listener);
	}

	public boolean contains(Node child) {
	     return children.contains(child);
	}
}

