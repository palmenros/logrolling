package com.logrolling.server.services.gifts;

import com.logrolling.server.exceptions.DataNotFoundException;
import com.logrolling.server.exceptions.NotEnoughGrolliesException;
import com.logrolling.server.integration.gifts.GiftsDAO;
import com.logrolling.server.services.authentication.AuthenticationService;

import java.util.ArrayList;
import java.util.List;

public class Gift {

    private int id;
    private String title;
    private String content;
    private int price;

    public Gift(int id, String title, String content, int price) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Gift(String title, String content, int price) {
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Gift(TransferGift g) {
        this.title = g.getTitle();
        this.content = g.getContent();
        this.price = g.getPrice();
    }

    public Gift() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public static List<TransferGift> getAllGifts() {
        List<Gift> gifts = GiftsDAO.getAllGifts();
        List<TransferGift> transfers = new ArrayList<TransferGift>();
        for (Gift g : gifts) {
            transfers.add(new TransferGift(g));
        }
        return transfers;
    }

    public static TransferGift getGiftByTitle(String title) {
        try {
            return new TransferGift(GiftsDAO.getGiftByTitle(title));
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        }
    }

    public static List<TransferPurchasedGift> getPurchasedGifts() {
        List<PurchasedGift> purchased = GiftsDAO.getPurchasedGifts();
        List<TransferPurchasedGift> transfers = new ArrayList<TransferPurchasedGift>();
        for (PurchasedGift p : purchased)
            transfers.add(new TransferPurchasedGift(p));
        return transfers;
    }

    public static void purchaseGift(String token, String title, String address) {
        String username = AuthenticationService.authenticateWithToken(token);
        Gift gift = GiftsDAO.getGiftByTitle(title);
        try {
            GiftsDAO.purchaseGift(username, new PurchasedGift(gift.getId(), address, username));
        } catch (NotEnoughGrolliesException e) {
            throw new NotEnoughGrolliesException();
        }
    }

}
