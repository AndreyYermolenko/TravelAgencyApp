package com.yermolenko.dao;

import com.yermolenko.model.Resort;

import java.util.List;

public interface ResortDAO {

    List<Resort> getResorts();

    Resort getResortById(int id);

}
