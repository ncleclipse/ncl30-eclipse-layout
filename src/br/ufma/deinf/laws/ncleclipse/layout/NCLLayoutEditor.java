/*******************************************************************************
 * This file is part of the NCL authoring environment - NCL Eclipse.
 *
 * Copyright (C) 2007-2012, LAWS/UFMA.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License version 2 for
 * more details. You should have received a copy of the GNU General Public 
 * License version 2 along with this program; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
 * 02110-1301, USA.
 *
 * For further information contact:
 * - ncleclipse@laws.deinf.ufma.br
 * - http://www.laws.deinf.ufma.br/ncleclipse
 * - http://www.laws.deinf.ufma.br
 *
 ******************************************************************************/
package br.ufma.deinf.laws.ncleclipse.layout;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import br.ufma.deinf.laws.ncleclipse.document.NCLSourceDocument;
import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.NodeCreationFactory;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;
import br.ufma.deinf.laws.ncleclipse.layout.part.AppEditPartFactory;
import br.ufma.deinf.laws.ncleclipse.layout.tree.AppTreeEditPartFactory;
import br.ufma.deinf.laws.ncleclipse.ncl.NCLDocument;
import br.ufma.deinf.laws.ncleclipse.ncl.NCLParser;
import br.ufma.deinf.laws.ncleclipse.ncl.NCLRegionBaseContentHandler;

public class NCLLayoutEditor extends GraphicalEditorWithPalette {
	private PaletteRoot paletteRoot;
	private NCLSourceDocument nclSourceDocument;
	
	public NCLLayoutEditor(){
		setEditDomain(new DefaultEditDomain(this));
	}
	
	public NCLSourceDocument getNclSourceDocument() {
		return nclSourceDocument;
	}
	
	public void setNclSourceDocument(NCLSourceDocument nclSourceDocument) {
		this.nclSourceDocument = nclSourceDocument;
	}

	/**
	 * parse the input file and create the model to Gef based on
	 * <regionBase> and <region> elements.
	 * @return
	 */
	public RegionBase createRegionBase(){
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		if(page == null) return new RegionBase();
		IEditorPart editor = page.getActiveEditor();
		IFile currentFile = ((FileEditorInput) getEditorInput()).getFile();
		
		String nclText;
		RegionBase psyEntreprise = new RegionBase();
		psyEntreprise.setId("RegionBase");
		try {
			NCLParser parser = new NCLParser();
			NCLRegionBaseContentHandler nclContentHandler = new NCLRegionBaseContentHandler();
			NCLDocument nclDocument = new NCLDocument();
			String text = nclSourceDocument.get();
			
			parser.setContentHandler(nclContentHandler);
			parser.doParse(text);
			
			psyEntreprise = nclContentHandler.getRegionBase();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return psyEntreprise;

	}
	

	@Override
	protected void initializeGraphicalViewer() {
		   GraphicalViewer viewer = getGraphicalViewer();
		   model = createRegionBase();
		   viewer.setContents(createRegionBase());

	}
	
	public void refreshGraphicalViewer(){
		GraphicalViewer viewer = getGraphicalViewer();
		model = createRegionBase();
		viewer.setContents(createRegionBase());
	}
	
	protected void configureGraphicalViewer() {
	       super.configureGraphicalViewer();
	       GraphicalViewer viewer = getGraphicalViewer();
	       viewer.setEditPartFactory(new AppEditPartFactory());
	       
	       keyHandler = new KeyHandler();
	       keyHandler.put(
	    		   	KeyStroke.getPressed(SWT.DEL, 127, 0),
	    		   	getActionRegistry().getAction(ActionFactory.DELETE.getId())
	    		   );
	       //viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON)
	       viewer.setKeyHandler(keyHandler);
	       //Isto foi um teste para ver se seta para cima e seta para baixo funcionavam
	       //viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
	}

	/**
	 * Refresh the source code
	 */
	public void refreshNCLSourceDocument() {
		List<Node> children = ((RegionBase)getGraphicalViewer().getContents().getModel()).getChildrenArray();
		for(int i = 0; i < children.size(); i++){
			refreshRegionInSource((Region) children.get(i));
		}
	}
	
	/**
	 * refresh each region in source code and call this recursive for each child
	 * @param rg
	 */
	private void refreshRegionInSource(Region rg){
		Integer it = rg.getHeight();
		nclSourceDocument.setAttribute(rg.getId(), "height", it.toString());
		it = rg.getWidth();
		nclSourceDocument.setAttribute(rg.getId(), "width", it.toString());
		it = rg.getTop();
		nclSourceDocument.setAttribute(rg.getId(), "top", it.toString());
		it = rg.getLeft();
		nclSourceDocument.setAttribute(rg.getId(), "left", it.toString());
		//faltando trabalhar com bottom e zIndex
		//Acho que bottom nao será preciso
		
		List<Node> children = rg.getChildrenArray();
		for(int i = 0; i < children.size(); i++){
			refreshRegionInSource((Region) children.get(i));
		}
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		//Isso é só para testar
		RegionTransformer.modelToText(model);
	}
	
	@Override
	public Object getAdapter(Class type) {
		// TODO Auto-generated method stub
		if(type == IContentOutlinePage.class)
				return new OutlinePage();
		return super.getAdapter(type);
	}
	
	private RegionBase model;
	private KeyHandler keyHandler;
	
    protected class OutlinePage extends ContentOutlinePage{
        private SashForm sash;
        public OutlinePage() {
              super(new TreeViewer());
        }
        public void createControl(Composite parent) {
              sash = new SashForm(parent, SWT.VERTICAL);
              getViewer().createControl(sash);
              getViewer().setEditDomain(getEditDomain());
              getViewer().setEditPartFactory(new AppTreeEditPartFactory());
              getViewer().setContents(model);
              getSelectionSynchronizer().addViewer(getViewer());
        }
        public void init(IPageSite pageSite) {
              super.init(pageSite);
              // On hook les actions de l'editeur sur la toolbar
              IActionBars bars = getSite().getActionBars();
              bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
getActionRegistry().getAction(ActionFactory.UNDO.getId()));
              bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
getActionRegistry().getAction(ActionFactory.REDO.getId()));
              bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
getActionRegistry().getAction(ActionFactory.DELETE.getId()));
              bars.updateActionBars();
              // On associe les raccourcis clavier de l'editeur a l'outline
              getViewer().setKeyHandler(keyHandler);
        }
        public Control getControl() {
              return sash;
        }
        public void dispose() {
              getSelectionSynchronizer().removeViewer(getViewer());
              super.dispose();
        }
   }

	@Override
	protected PaletteRoot getPaletteRoot() {
		if(paletteRoot == null){	
			paletteRoot = new PaletteRoot();
			PaletteGroup manipGroup = new PaletteGroup("Manipulação dos Objetos");
			paletteRoot.add(manipGroup);
			SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
			manipGroup.add(selectionToolEntry);
			manipGroup.add(new MarqueeToolEntry());
			paletteRoot.setDefaultEntry(selectionToolEntry);
			PaletteSeparator sep2 = new PaletteSeparator();
			paletteRoot.add(sep2);
			PaletteGroup instGroup = new PaletteGroup("Criar Regiões");
			paletteRoot.add(instGroup);
			instGroup.add(new CreationToolEntry("New Region", "Criar nova região",
			           new NodeCreationFactory(Region.class),
			           null, null));

		}
		return paletteRoot;
	}
	
	/**
	 * This is a test
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{
		updateActions(getSelectionActions());
	}
}
