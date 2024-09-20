package com.task.soaltestkerja.service;


import com.task.soaltestkerja.dto.BorrowDto;
import com.task.soaltestkerja.dto.BorrowResponse;

import java.util.List;

public interface IBorrowService {

    void createBorrow(BorrowDto borrowDto);

    List<BorrowResponse> getAllDataBorrow();

    void returningBorrow(BorrowDto borrowDto);

}
