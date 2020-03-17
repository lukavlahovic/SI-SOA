/***********************************************************************
 * Module:  Repository.java
 * Author:  PC
 * Purpose: Defines the Interface Repository
 ***********************************************************************/

package appFramework;

import java.sql.SQLException;
import java.util.*;

import tree.treeModel.Entitet;


public interface Repository {
   
   void add(Entitet entitet, String[] redKojiSeDodaje) throws SQLException;
   void remove(Entitet entitet, String[] redKojiSeDodaje) throws SQLException;
   void update(Entitet entitet, String[] redKojiSeDodaje, String staraVrednost) throws SQLException;
   void save();

}