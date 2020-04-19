package com.creams.temo.mapper;


import com.creams.temo.entity.Saves;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SavesMapper {

    List<Saves> querySaves(@Param("case_id") String caseId);

    void addSaves(Saves savesRequest);

    void updateSavesById(Saves savesRequest);


    void deleteSaves(@Param("case_id") String caseId);
}
