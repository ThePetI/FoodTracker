/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.issuetracker.repositories;

import hu.elte.issuetracker.entities.Drinks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Solti
 */
@Repository
public interface DrinksRepository extends CrudRepository<Drinks, Integer> {

}
