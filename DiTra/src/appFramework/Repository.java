/***********************************************************************
 * Module:  Repository.java
 * Author:  PC
 * Purpose: Defines the Interface Repository
 ***********************************************************************/

package appFramework;

import java.util.*;

/** @pdOid 806d2acf-0a00-4c63-b373-71fb7251ada1 */
public interface Repository {
   /** @pdOid a985ef95-0727-4b9c-9344-b26497c987ae */
   void add();
   /** @pdOid 5e48effe-b41f-42d4-a51a-858e609c78f1 */
   void remove();
   /** @pdOid 53701339-b14d-440d-81ed-cf97fd31c158 */
   void update();
   /** @pdOid c236c923-9dbb-4347-87b9-d56f84fad8fc */
   void save();

}