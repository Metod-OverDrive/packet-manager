package com.example.packetmanager.service;

import com.example.packetmanager.domain.payload.RecordNode;

import java.util.List;

public interface PayloadService {

    List<RecordNode> getRecords(int[] values, boolean isCompressed);

}
