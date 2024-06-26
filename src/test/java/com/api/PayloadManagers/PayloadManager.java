package com.api.PayloadManagers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.Payloads.AuthPayload;
import com.api.Payloads.Booking;
import com.api.Payloads.Bookingdates;

public class PayloadManager {

    // JAVA -> JSON
    private ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatedPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(199);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Breakfast, lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-10-01");
        bookingdates.setCheckout("2022-10-01");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }

    public String updatePayload(){
        return null;
    }

    public String createAuthPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        AuthPayload authPayload = new AuthPayload();
        authPayload.setUsername("admin");
        authPayload.setPassword("password123");

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authPayload);
    }
}
