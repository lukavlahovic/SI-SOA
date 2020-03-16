/***********************************************************************
 * Module:  NodeFactory.java
 * Author:  PC
 * Purpose: Defines the Class NodeFactory
 ***********************************************************************/

package tree.treeModel;

import java.util.*;

/** @pdOid a8cdb4d6-5ad3-4703-bd1e-c8832393d091 */
public class NodeFactory {

	
	
   public NodeFactory() {

	}

   public Node makeNode(String type) {
      
	   if(type.equals("informacioni resurs"))
		   return new InformacioniResurs();
	   else if(type.equals("entitet"))
		   		return new Entitet();
	   else if(type.equals("atribut"))
		   		return new Atribut();
	   
	   return null;
    }

}