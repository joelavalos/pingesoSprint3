/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionbeans;

import entities.Episodios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joel
 */
@Local
public interface EpisodiosFacadeLocal {

    void create(Episodios episodios);

    void edit(Episodios episodios);

    void remove(Episodios episodios);

    Episodios find(Object id);

    List<Episodios> findAll();

    List<Episodios> findRange(int[] range);

    int count();
    
}
