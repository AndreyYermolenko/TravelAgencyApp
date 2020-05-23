package com.yermolenko.dao;

import com.yermolenko.model.AgencyBranch;

import java.util.List;

public interface AgencyBranchDAO {

    List<AgencyBranch> getAgencyBranches();

    AgencyBranch getAgencyBranchById(int id);


}
