package br.ufma.deinf.laws.ncleclipse.layout;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.xml.sax.InputSource;

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
	
	
	public NCLLayoutEditor(){
		setEditDomain(new DefaultEditDomain(this));
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
		IEditorPart editor = page.getActiveEditor();
		IFile currentFile = ((FileEditorInput) getEditorInput()).getFile();
		
		String nclText;
		RegionBase psyEntreprise = new RegionBase();
		psyEntreprise.setId("RegionBase");
		try {
			NCLParser parser = new NCLParser();
			NCLRegionBaseContentHandler nclContentHandler = new NCLRegionBaseContentHandler();
			NCLDocument nclDocument = new NCLDocument();
			
			parser.setContentHandler(nclContentHandler);
			parser.doParse(new InputSource(currentFile.getContents()));
			
			psyEntreprise = nclContentHandler.getRegionBase();
			
		} catch (CoreException e) {
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
}
