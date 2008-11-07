package br.ufma.deinf.laws.ncleclipse.layout;

import java.util.List;

import br.ufma.deinf.laws.ncleclipse.layout.model.Node;
import br.ufma.deinf.laws.ncleclipse.layout.model.Region;
import br.ufma.deinf.laws.ncleclipse.layout.model.RegionBase;

public class RegionTransformer {
	public static void modelToText(Node node){
		if(node instanceof RegionBase)
			System.out.println("RegionBase: " +node.getId());
		else {
			Region rg = (Region)node;
			System.out.println("Region:" + rg.getId() + " top="+rg.getTop()+" w="+rg.getWidth()+ " height="+rg.getHeight());	
		}
		List<Node> children = node.getChildrenArray();
		for(int i = 0; i < children.size(); i++)
			modelToText(children.get(i));
	}
	
}
