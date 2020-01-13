package com.li.service;

import com.li.support.dto.ExamineInfoDTO;

public interface IExamineInfoService {
    void save(ExamineInfoDTO examineInfoDTO);
    void examine(ExamineInfoDTO examineInfoDTO);
}
