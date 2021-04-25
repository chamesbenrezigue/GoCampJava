/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gocamp.IService;

import gocamp.entity.reservation;

import java.util.List;

/**
 *
 * @author HAMMOUDA
 */
public interface IReservationService {
    
        public void createReservation(reservation r );

        public List<reservation> getAll();
        
        public void update(reservation e,int id);

        public void delete(int id);
    
}
