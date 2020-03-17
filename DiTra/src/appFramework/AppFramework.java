/***********************************************************************
 * Module:  AppFramework.java
 * Author:  User
 * Purpose: Defines the Class AppFramework
 ***********************************************************************/

package appFramework;

import java.util.*;

import database.config.ConfigImplementation;
import gui.GuiImplementation;
import tree.treeModel.TreeModelImplementacion;

public abstract class AppFramework {
   
	public java.util.Collection<GuiComponent> guiComponent;
   	public java.util.Collection<TreeModel> treeModel;
   	public java.util.Collection<ConfigComponent> configComponent;
   	public java.util.Collection<Repository> repository;
   
   	private GuiComponent gui;
   	private TreeModel tree;
   	private ConfigComponent config;
   
   	public void create() {
	   gui = new GuiImplementation();
	   config = new ConfigImplementation();
	   tree = new TreeModelImplementacion(getConfig());
   	}
   	
	public GuiImplementation getGui() {
		return (GuiImplementation) gui;
	}
	
	public TreeModelImplementacion getTree() {
		return (TreeModelImplementacion) tree;
	}
	

	public ConfigImplementation getConfig() {
		return (ConfigImplementation)config;
	}

//-----------------------------IZ PD15--------------------------------------------
/** @pdGenerated default getter */
   public java.util.Collection<GuiComponent> getGuiComponent() {
      if (guiComponent == null)
         guiComponent = new java.util.HashSet<GuiComponent>();
      return guiComponent;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorGuiComponent() {
      if (guiComponent == null)
         guiComponent = new java.util.HashSet<GuiComponent>();
      return guiComponent.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newGuiComponent */
   public void setGuiComponent(java.util.Collection<GuiComponent> newGuiComponent) {
      removeAllGuiComponent();
      for (java.util.Iterator iter = newGuiComponent.iterator(); iter.hasNext();)
         addGuiComponent((GuiComponent)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newGuiComponent */
   public void addGuiComponent(GuiComponent newGuiComponent) {
      if (newGuiComponent == null)
         return;
      if (this.guiComponent == null)
         this.guiComponent = new java.util.HashSet<GuiComponent>();
      if (!this.guiComponent.contains(newGuiComponent))
         this.guiComponent.add(newGuiComponent);
   }
   
   /** @pdGenerated default remove
     * @param oldGuiComponent */
   public void removeGuiComponent(GuiComponent oldGuiComponent) {
      if (oldGuiComponent == null)
         return;
      if (this.guiComponent != null)
         if (this.guiComponent.contains(oldGuiComponent))
            this.guiComponent.remove(oldGuiComponent);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllGuiComponent() {
      if (guiComponent != null)
         guiComponent.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<TreeModel> getTreeModel() {
      if (treeModel == null)
         treeModel = new java.util.HashSet<TreeModel>();
      return treeModel;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTreeModel() {
      if (treeModel == null)
         treeModel = new java.util.HashSet<TreeModel>();
      return treeModel.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTreeModel */
   public void setTreeModel(java.util.Collection<TreeModel> newTreeModel) {
      removeAllTreeModel();
      for (java.util.Iterator iter = newTreeModel.iterator(); iter.hasNext();)
         addTreeModel((TreeModel)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTreeModel */
   public void addTreeModel(TreeModel newTreeModel) {
      if (newTreeModel == null)
         return;
      if (this.treeModel == null)
         this.treeModel = new java.util.HashSet<TreeModel>();
      if (!this.treeModel.contains(newTreeModel))
         this.treeModel.add(newTreeModel);
   }
   
   /** @pdGenerated default remove
     * @param oldTreeModel */
   public void removeTreeModel(TreeModel oldTreeModel) {
      if (oldTreeModel == null)
         return;
      if (this.treeModel != null)
         if (this.treeModel.contains(oldTreeModel))
            this.treeModel.remove(oldTreeModel);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTreeModel() {
      if (treeModel != null)
         treeModel.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<ConfigComponent> getConfigComponent() {
      if (configComponent == null)
         configComponent = new java.util.HashSet<ConfigComponent>();
      return configComponent;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorConfigComponent() {
      if (configComponent == null)
         configComponent = new java.util.HashSet<ConfigComponent>();
      return configComponent.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newConfigComponent */
   public void setConfigComponent(java.util.Collection<ConfigComponent> newConfigComponent) {
      removeAllConfigComponent();
      for (java.util.Iterator iter = newConfigComponent.iterator(); iter.hasNext();)
         addConfigComponent((ConfigComponent)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newConfigComponent */
   public void addConfigComponent(ConfigComponent newConfigComponent) {
      if (newConfigComponent == null)
         return;
      if (this.configComponent == null)
         this.configComponent = new java.util.HashSet<ConfigComponent>();
      if (!this.configComponent.contains(newConfigComponent))
         this.configComponent.add(newConfigComponent);
   }
   
   /** @pdGenerated default remove
     * @param oldConfigComponent */
   public void removeConfigComponent(ConfigComponent oldConfigComponent) {
      if (oldConfigComponent == null)
         return;
      if (this.configComponent != null)
         if (this.configComponent.contains(oldConfigComponent))
            this.configComponent.remove(oldConfigComponent);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllConfigComponent() {
      if (configComponent != null)
         configComponent.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Repository> getRepository() {
      if (repository == null)
         repository = new java.util.HashSet<Repository>();
      return repository;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRepository() {
      if (repository == null)
         repository = new java.util.HashSet<Repository>();
      return repository.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRepository */
   public void setRepository(java.util.Collection<Repository> newRepository) {
      removeAllRepository();
      for (java.util.Iterator iter = newRepository.iterator(); iter.hasNext();)
         addRepository((Repository)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRepository */
   public void addRepository(Repository newRepository) {
      if (newRepository == null)
         return;
      if (this.repository == null)
         this.repository = new java.util.HashSet<Repository>();
      if (!this.repository.contains(newRepository))
         this.repository.add(newRepository);
   }
   
   /** @pdGenerated default remove
     * @param oldRepository */
   public void removeRepository(Repository oldRepository) {
      if (oldRepository == null)
         return;
      if (this.repository != null)
         if (this.repository.contains(oldRepository))
            this.repository.remove(oldRepository);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRepository() {
      if (repository != null)
         repository.clear();
   }

}