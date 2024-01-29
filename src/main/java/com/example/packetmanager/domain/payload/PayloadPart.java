package com.example.packetmanager.domain.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель, представляющая собой полученный пакет данных.
 */
@Getter
@Setter
@ToString
public class PayloadPart {

    private List<RecordNode> recordNodes = new ArrayList<>();

    public boolean addRecord(RecordNode node) {
        return this.recordNodes.add(node);
    }

}
