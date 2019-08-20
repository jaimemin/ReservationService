package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.FileInfo;

@Repository
public interface FileInfoDao {
	int insertFileInfos(@Param("fileInfos") List<FileInfo> fileInfos);
}
