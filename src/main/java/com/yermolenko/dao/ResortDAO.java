package com.yermolenko.dao;

import com.yermolenko.model.Resort;

import java.util.List;

/**
 * Interface ResortDAO.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
public interface ResortDAO {

    /**
     * Method getResorts returns the resorts of this ResortDAO object.
     *
     *
     *
     * @return the resorts (type List<Resort>) of this ResortDAO object.
     */
    List<Resort> getResorts();

    /**
     * Method getResortById.
     *
     * @param id of type int
     * @return Resort
     */
    Resort getResortById(int id);

}
