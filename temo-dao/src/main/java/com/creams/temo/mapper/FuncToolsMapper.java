package com.creams.temo.mapper;

import com.creams.temo.model.FuncToolsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FuncToolsMapper {

    void saveFunc(FuncToolsDto funcToolsDto);

    List<FuncToolsDto> findFunc(FuncToolsDto funcToolsDto);

    void deleteFuncById(Integer id,String creator);

    void updateFuncById(FuncToolsDto funcToolsDto);
}
