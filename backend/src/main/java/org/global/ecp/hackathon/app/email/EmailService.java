package org.global.ecp.hackathon.app.email;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.basket.model.BasketProduct;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    // TODO - Task 11: send a simple mail message to the email server
    public void sendEmail(final Order newOrder) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // Set the email sender
            message.setFrom("no-reply@sonixwave.com");

            // Set the recipient email
            message.setTo("task@submission.com");

            // Set the email subject with the order ID
            message.setSubject("Order ID: " + newOrder.getId());

            // Set the email body
            StringBuilder bodyBuilder = new StringBuilder();
            bodyBuilder.append("Date of order: ").append(newOrder.getDateTimeOfOrder().toLocalDate()).append("\n");
            bodyBuilder.append("Time of order: ").append(newOrder.getDateTimeOfOrder().toLocalTime()).append("\n");
            bodyBuilder.append("Ordered Products:\n");
            for (BasketProduct product : newOrder.getOrderedProducts()) {
                bodyBuilder.append(product.getProduct().getName()).append("\n");
                bodyBuilder.append(product.getProduct().getDescription()).append(" £").append(product.getProduct().getPrice()).append("\n");
                bodyBuilder.append("Quantity x ").append(product.getQuantity()).append("\n");
            }
            bodyBuilder.append("Total cost of order: £").append(newOrder.getTotalCost()).append("\n");

            message.setText(bodyBuilder.toString());
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
