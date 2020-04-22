package com.logrolling.server.controllers;

import com.logrolling.server.transfer.TransferGift;

import java.util.List;

public interface GiftDao {
    public List<TransferGift> getAllGifts();
    public TransferGift getGift(String title);
    public TransferGift getPrice(String title);
    public void createGift(TransferGift newGift);
    public void updateGiftByName(TransferGift newGift);
    public void deleteGift(String title);
}
