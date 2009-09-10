/*******************************************************************************
 * This file is part of the authoring environment in Nested Context Language -
 * NCL Eclipse.
 * 
 * Copyright: 2007-2009 UFMA/LAWS (Laboratory of Advanced Web Systems), All Rights Reserved.
 * 
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU General Public License version 2 for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License version 2
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 * For further information contact:
 * 		ncleclipse@laws.deinf.ufma.br
 * 		http://www.laws.deinf.ufma.br/ncleclipse
 * 		http://www.laws.deinf.ufma.br
 ********************************************************************************/
package br.ufma.deinf.laws.ncleclipse.layout.model;

/**
 * 
 * @author Roberto Azevedo <roberto@laws.deinf.ufma.br>
 * 
 */
public class Region extends Node {
	// Id declared in Node
	private String title;
	private String bottom;
	private String zIndex;

	public Region() {
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
		if (top.endsWith("px"))
			this.layout.y = new Integer(top.substring(0, top.length() - 2));
		else if (top.endsWith("%"))
			this.y_perc = top.substring(0, top.length() - 1);
		else
			this.layout.y = new Integer(top);
	}

	public void setTop(int top) {
		this.layout.y = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		if (bottom.endsWith("px"))
			this.layout.height = new Integer(bottom.substring(0, bottom
					.length() - 2));
		else if (bottom.endsWith("%"))
			this.height_perc = bottom.substring(0, bottom.length() - 1);
		else
			this.layout.height = new Integer(bottom);
	}

	public int getHeight() {
		return layout.height;
	}

	public void setHeight(String height) {
		if (height.endsWith("px"))
			this.layout.height = new Integer(height.substring(0, height
					.length() - 2));
		else if (height.endsWith("%"))
			this.height_perc = height.substring(0, height.length() - 1);
		else
			this.layout.height = new Integer(height);
	}

	public void setHeigth(int height) {
		this.layout.height = height;
	}

	public int getWidth() {
		return this.layout.width;
	}

	public void setWidth(String width) {
		if (width.endsWith("px"))
			this.layout.width = new Integer(width.substring(0,
					width.length() - 2));
		else if (width.endsWith("%"))
			this.width_perc = width.substring(0, width.length() - 1);
		else
			this.layout.width = new Integer(width);
	}

	public void setWidth(int width) {
		this.layout.width = width;
	}

	public String getZIndex() {
		return zIndex;
	}

	public void setZIndex(String index) {
		zIndex = index;
	}

}
