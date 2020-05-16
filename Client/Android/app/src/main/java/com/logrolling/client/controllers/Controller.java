package com.logrolling.client.controllers;

import com.logrolling.client.delegates.ChatDelegate;
import com.logrolling.client.delegates.FavorDelegate;
import com.logrolling.client.delegates.GiftDelegate;
import com.logrolling.client.delegates.TokenDelegate;
import com.logrolling.client.delegates.UserDelegate;
import com.logrolling.client.services.AuthenticationService;
import com.logrolling.client.services.LocationService;
import com.logrolling.client.transfer.Coordinates;
import com.logrolling.client.transfer.Filter;
import com.logrolling.client.transfer.TransferChat;
import com.logrolling.client.transfer.TransferCredentials;
import com.logrolling.client.transfer.TransferFavor;
import com.logrolling.client.transfer.TransferGift;
import com.logrolling.client.transfer.TransferMessage;
import com.logrolling.client.transfer.TransferMessagePreview;
import com.logrolling.client.transfer.TransferPurchase;
import com.logrolling.client.transfer.TransferToken;
import com.logrolling.client.transfer.TransferUser;
import com.logrolling.client.web.ErrorListener;
import com.logrolling.client.web.ResponseListener;
import com.logrolling.client.web.SuccessListener;

import java.util.Date;

public class Controller {

    private static Controller instance;

    //Delegates
    private TokenDelegate tokenDelegate;
    private ChatDelegate chatDelegate;
    private FavorDelegate favorDelegate;
    private GiftDelegate giftDelegate;
    private UserDelegate userDelegate;

    private Controller() {
        tokenDelegate = new TokenDelegate();
        chatDelegate = new ChatDelegate();
        favorDelegate = new FavorDelegate();
        giftDelegate = new GiftDelegate();
        userDelegate = new UserDelegate();
    }

    static public Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    ///////////////////////////////////////
    //          TOKEN DELEGATE           //
    ///////////////////////////////////////

    public void login(String username, String password, ResponseListener<TransferToken> responseListener, ErrorListener errorListener) {
        tokenDelegate.login(new TransferCredentials(username, password), responseListener, errorListener);
    }

    ///////////////////////////////////////
    //          CHAT DELEGATE            //
    ///////////////////////////////////////

    public void getChatWithUser(String username, ResponseListener<TransferChat> responseListener, ErrorListener errorListener) {
        chatDelegate.getChatWithUser(username, responseListener, errorListener);
    }

    public void getChatPreviews(ResponseListener<TransferMessagePreview[]> responseListener, ErrorListener errorListener) {
        chatDelegate.getChatPreviews(responseListener, errorListener);
    }

    public void sendMessage(String to, String content, SuccessListener successListener, ErrorListener errorListener) {
        chatDelegate.sendMessage(
                new TransferMessage(AuthenticationService.getInstance().getAuthenticatedUsername(), to, content),
                successListener,
                errorListener
        );
    }

    ///////////////////////////////////////
    //          FAVOR DELEGATE           //
    ///////////////////////////////////////

    public void getAvailableFavorsFiltered(int minGrollies, double maxDistance, int minDate, ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        favorDelegate.getAvailableFavorsFiltered(new Filter(minGrollies, LocationService.getInstance().getLocation(), maxDistance, minDate), responseListener, errorListener);
    }

    public void getCreatedFavors(ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        favorDelegate.getCreatedFavors(responseListener, errorListener);
    }

    public void doFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        favorDelegate.doFavor(favorId, successListener, errorListener);
    }

    public void completeFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        favorDelegate.completeFavor(favorId, successListener, errorListener);
    }

    public void getFavorsToBeDone(ResponseListener<TransferFavor[]> responseListener, ErrorListener errorListener) {
        favorDelegate.getFavorsToBeDone(responseListener, errorListener);
    }

    public void createFavor(String title, String description, Date dueTime, int reward, Coordinates coordinates, SuccessListener successListener, ErrorListener errorListener) {
        favorDelegate.createFavor(new TransferFavor(
                AuthenticationService.getInstance().getAuthenticatedUsername(),
                title,
                description,
                (int) (dueTime.getTime() / 1000),
                reward,
                coordinates,
                null,
                false
        ), successListener, errorListener);
    }

    public void updateFavor(int favorId, String title, String description, Date dueTime, int reward, Coordinates coordinates, SuccessListener successListener, ErrorListener errorListener) {
        favorDelegate.updateFavor(new TransferFavor(
                favorId, AuthenticationService.getInstance().getAuthenticatedUsername(),
                title,
                description,
                (int) (dueTime.getTime() / 1000),
                reward,
                coordinates,
                null,                   //Favors can only be updated when not yet assigned
                false
        ), favorId, successListener, errorListener);
    }

    public void deleteFavor(int favorId, SuccessListener successListener, ErrorListener errorListener) {
        favorDelegate.deleteFavor(favorId, successListener, errorListener);
    }

    ///////////////////////////////////////
    //          GIFT DELEGATE            //
    ///////////////////////////////////////

    public void getAllGifts(ResponseListener<TransferGift[]> responseListener, ErrorListener errorListener) {
        giftDelegate.getAllGifts(responseListener, errorListener);
    }

    public void purchaseGift(String giftName, String address, SuccessListener successListener, ErrorListener errorListener) {
        LocationService locationService = LocationService.getInstance();
        giftDelegate.purchaseGift(new TransferPurchase(
                giftName,
                address
        ), successListener, errorListener);
    }

    ///////////////////////////////////////
    //          USER DELEGATE            //
    ///////////////////////////////////////

    public void getCurrentUser(ResponseListener<TransferUser> responseListener, ErrorListener errorListener) {
        userDelegate.getLoggedUser(
                responseListener,
                errorListener
        );
    }

    public void getCurrentUserGrollies(ResponseListener<Integer> responseListener, ErrorListener errorListener) {
        getCurrentUser((transferUser) -> {
            responseListener.onResponse(transferUser.getGrollies());
        }, errorListener);
    }

    public void registerUser(String username, String password, SuccessListener successListener, ErrorListener errorListener) {
        userDelegate.registerUser(new TransferCredentials(username, password), successListener, errorListener);
    }

    public void updatePassword(String newPassword, SuccessListener successListener, ErrorListener errorListener) {
        userDelegate.updateUser(new TransferCredentials(
                AuthenticationService.getInstance().getAuthenticatedUsername(),
                newPassword
        ), successListener, errorListener);
    }

    public void deleteUser(SuccessListener successListener, ErrorListener errorListener) {
        userDelegate.deleteUser(successListener, errorListener);
    }

}
