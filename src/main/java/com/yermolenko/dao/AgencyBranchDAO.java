package com.yermolenko.dao;

import com.yermolenko.model.AgencyBranch;

import java.util.List;

/**
 * Interface AgencyBranchDAO.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
public interface AgencyBranchDAO {

    /**
     * Method getAgencyBranches returns the agencyBranches of this AgencyBranchDAO object.
     *
     *
     *
     * @return the agencyBranches (type List<AgencyBranch>) of this AgencyBranchDAO object.
     */
    List<AgencyBranch> getAgencyBranches();

    /**
     * Method getAgencyBranchById.
     *
     * @param id of type int
     * @return AgencyBranch
     */
    AgencyBranch getAgencyBranchById(int id);


}
