package com.example.EcommerceMindhub.utils;

import com.example.EcommerceMindhub.services.implementService.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

public class EmailSenderUtils {

    public EmailSenderUtils() {
    }

    public void sendEmail(EmailSenderService emailSenderService, String toEmail){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                emailSenderService.sendEmail(toEmail,
                        "Tenés una compra pendiente!",
                        "Hola!, hemos detectado que tenés productos en tu carrito de compras." +
                                " Finalizá tu compra y llevate un 10% de descuento!");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 10000);
    }

}
