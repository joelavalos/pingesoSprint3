/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Muesta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joel
 */
@Local
public interface MuestaFacadeLocal {

    void create(Muesta muesta);

    void edit(Muesta muesta);

    void remove(Muesta muesta);

    Muesta find(Object id);

    List<Muesta> findAll();

    List<Muesta> findRange(int[] range);

    int count();
    
}
