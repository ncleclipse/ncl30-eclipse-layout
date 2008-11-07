/******************************************************************************
Este arquivo é parte da implementação do ambiente de autoria em Nested Context
Language - NCL Eclipse.

Direitos Autorais Reservados (c) 2007-2008 UFMA/LAWS (Laboratório de Sistemas Avançados da Web) 

Este programa é software livre; você pode redistribuí-lo e/ou modificá-lo sob 
os termos da Licença Pública Geral GNU versão 2 conforme publicada pela Free 
Software Foundation.

Este programa é distribuído na expectativa de que seja útil, porém, SEM 
NENHUMA GARANTIA; nem mesmo a garantia implícita de COMERCIABILIDADE OU 
ADEQUAÇÃO A UMA FINALIDADE ESPECÍFICA. Consulte a Licença Pública Geral do 
GNU versão 2 para mais detalhes. 

Você deve ter recebido uma cópia da Licença Pública Geral do GNU versão 2 junto 
com este programa; se não, escreva para a Free Software Foundation, Inc., no 
endereço 59 Temple Street, Suite 330, Boston, MA 02111-1307 USA. 

Para maiores informações:
ncleclipse@laws.deinf.ufma.br
http://www.laws.deinf.ufma.br/ncleclipse
http://www.laws.deinf.ufma.br

******************************************************************************
This file is part of the authoring environment in Nested Context Language -
NCL Eclipse.

Copyright: 2007-2008 UFMA/LAWS (Laboratory of Advanced Web Systems), All Rights Reserved.

This program is free software; you can redistribute it and/or modify it under 
the terms of the GNU General Public License version 2 as published by
the Free Software Foundation.

This program is distributed in the hope that it will be useful, but WITHOUT ANY 
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE.  See the GNU General Public License version 2 for more 
details.

You should have received a copy of the GNU General Public License version 2
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA

For further information contact:
ncleclipse@laws.deinf.ufma.br
http://www.laws.deinf.ufma.br/ncleclipse
http://www.laws.deinf.ufma.br

*******************************************************************************/

package br.ufma.deinf.laws.ncleclipse.ncl;

import java.util.Stack;

import org.eclipse.draw2d.geometry.Rectangle;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import br.ufma.deinf.laws.ncleclipse.layout.model.Region;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;


public class NCLRegionBaseContentHandler implements ContentHandler{
	private RegionBase regionBase = null;
	Stack <Region> regionStack = null;
	
	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endElement(String localName, String arg1, String arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("end element " + arg1);
		if(arg1.equals("region"))
		{
			regionStack.pop();
		}
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		if(regionStack == null)
			regionStack = new Stack();
	}

	@Override
	public void startElement(String URI, String localName, String qName,
			Attributes atts) throws SAXException {
			System.out.println("start element: " + localName+ " " + qName);
			if(localName.equals("regionBase")){
				regionBase = new RegionBase();
			}
			else if(localName.equals("region")){
				Region rg = new Region();
				rg.setLayout(new Rectangle(0, 0, 400, 400));

				//get the attributes from the document an set to model
				if(atts.getValue("id") != null)
					rg.setId(atts.getValue("id"));
				if(atts.getValue("height") != null)
					rg.setHeight(atts.getValue("height"));
				if(atts.getValue("width") != null)
					rg.setWidth(atts.getValue("width"));
				if(atts.getValue("top") != null)
					rg.setTop(atts.getValue("top"));
				if(atts.getValue("left") != null)
					rg.setLeft(atts.getValue("left"));
				
				if(regionStack.size() == 0){
					regionBase.addChild(rg);
				}
				else regionStack.firstElement().addChild(rg);
				regionStack.push(rg);
			}
		
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public RegionBase getRegionBase() {
		return regionBase;
	}

	public void setNclDocument(NCLDocument nclDocument) {
		this.regionBase = regionBase;
	}
	
}
